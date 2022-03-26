package com.dreamvalley.demo.axon.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 雪花算法ID生成器
 * 生成的是线程安全的递增的ID，分布式系统内不会重复
 */
public class IdWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdWorker.class);
    /*
     *//**
     * 开始时间截，用程序开始使用的时间
     */
    private final long twepoch = 1614316099758L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 8L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 2L;

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 工作机器ID(0~255)
     */
    private final long workerId;

    /**
     * 数据中心ID(0~3)
     */
    private final long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;


    // idWorker单例的，多例或分布式系统的话构造参数不能与其他实例相同，否则会产生安全问题

    private static final IdWorker ID_WORKER;

    static {
        String workIp = System.getProperty("POD_OWN_IP_ADDRESS");
        if (StringUtils.isEmpty(workIp)){
            workIp = System.getenv("POD_OWN_IP_ADDRESS");
        }
        LOGGER.info("接收到的workIp为:{}", workIp);
        long workerId = 0L;
        if (!StringUtils.isEmpty(workIp)) {
            String[] split = workIp.split("\\.");
            workerId = Long.parseLong(split[split.length - 1]);
        }
        LOGGER.info("计算的workId为:{}", workerId);
        long dataCenterId = workerId % 3;
        ID_WORKER = new IdWorker(workerId, dataCenterId);
    }


    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    private IdWorker(long workerId, long datacenterId) {
        /**
         * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
         */
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        /**
         * 支持的最大数据标识id，结果是31
         */
        long maxDatacenterId = ~(-1L << datacenterIdBits);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }


    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return long
     */
    private synchronized long nextId() {

        long timestamp = timeGen();
        timestamp = generateId(timestamp);
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    private long generateId(long timestamp) {
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            /**
             * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
             */
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0)
            //阻塞到下一个毫秒,获得新的时间戳
            {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else//时间戳改变，毫秒内序列重置
        {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        return timestamp;
    }

    /**
     * 获得下一个ID (string)
     **/
    private synchronized String generateNextId() {
        long timestamp = timeGen();
        timestamp = generateId(timestamp);
        //移位并通过或运算拼到一起组成64位的ID
        return String.valueOf(((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence);
    }


    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 公开方法，用于生成一个ID
     *
     * @return
     */
    public static long getNextId() {
        return ID_WORKER.nextId();
    }

    public static void main(String[] args) throws InterruptedException {
        /*ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100000, 1000000, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000000));
        CountDownLatch cdl = new CountDownLatch(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(IdWorker.getNextId() + "\t" + cdl.getCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        cdl.countDown();
                    }
                }
            });
        }
        cdl.await();

        // 1 0111 1100 0110 0100 0000 1110 0011 1111 1011 0000 1100 1100 00 11 0000 0000 00

        System.out.println(System.currentTimeMillis());*/


    }

}
