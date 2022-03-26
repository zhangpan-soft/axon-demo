package com.dreamvalley.demo.axon.order;

import com.dreamvalley.demo.axon.core.base.repository.CustomSimpleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.dreamvalley.demo")
@EnableJpaRepositories(repositoryBaseClass = CustomSimpleRepository.class)
@EnableDiscoveryClient
@EnableFeignClients
public class AxonDemoOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonDemoOrderApplication.class, args);
    }

}
