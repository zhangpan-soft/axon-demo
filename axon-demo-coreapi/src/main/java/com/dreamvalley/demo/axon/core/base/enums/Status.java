package com.dreamvalley.demo.axon.core.base.enums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 异常枚举类
 *
 * @author zhangpan
 * @date 2021/6/10/010 14:31
 */
public enum Status {
    /**
     * 1XX
     */
    CONTINUE(1000, "Continue"),
    SWITCHING_PROTOCOLS(1001, "Switching Protocols"),
    PROCESSING(1002, "Processing"),
    CHECKPOINT(1003, "Checkpoint"),

    /**
     * 2XX
     */
    OK(2000, "成功"),
    CREATED(2001, "Created"),
    ACCEPTED(2002, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(2003, "Non-Authoritative Information"),
    NO_CONTENT(2004, "No Content"),
    RESET_CONTENT(2005, "Reset Content"),
    PARTIAL_CONTENT(2006, "Partial Content"),
    MULTI_STATUS(2007, "Multi-Status"),
    ALREADY_REPORTED(2008, "Already Reported"),
    IM_USED(2026, "IM Used"),

    /**
     * 3XX
     */
    MULTIPLE_CHOICES(3000, "Multiple Choices"),
    MOVED_PERMANENTLY(3001, "Moved Permanently"),
    FOUND(3002, "Found"),
    @Deprecated
    MOVED_TEMPORARILY(3002, "Moved Temporarily"),
    SEE_OTHER(3003, "See Other"),
    NOT_MODIFIED(3004, "Not Modified"),
    /**
     * @deprecated
     */
    @Deprecated
    USE_PROXY(3005, "Use Proxy"),
    TEMPORARY_REDIRECT(3007, "Temporary Redirect"),
    PERMANENT_REDIRECT(3008, "Permanent Redirect"),

    BAD_REQUEST(4000, "Bad Request"),
    UNAUTHORIZED(4001, "Unauthorized"),
    PAYMENT_REQUIRED(4002, "Payment Required"),
    FORBIDDEN(4003, "Forbidden"),
    NOT_FOUND(4004, "Not Found"),
    METHOD_NOT_ALLOWED(4005, "Method Not Allowed"),
    NOT_ACCEPTABLE(4006, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(4007, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(4008, "Request Timeout"),
    CONFLICT(4009, "Conflict"),
    GONE(4010, "Gone"),
    LENGTH_REQUIRED(4011, "Length Required"),
    PRECONDITION_FAILED(4012, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(4013, "Payload Too Large"),
    /**
     * @deprecated
     */
    @Deprecated
    REQUEST_ENTITY_TOO_LARGE(4013, "Request Entity Too Large"),
    URI_TOO_LONG(4014, "URI Too Long"),
    /**
     * @deprecated
     */
    @Deprecated
    REQUEST_URI_TOO_LONG(4014, "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(4015, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(4016, "Requested range not satisfiable"),
    EXPECTATION_FAILED(4017, "Expectation Failed"),
    I_AM_A_TEAPOT(4018, "I'm a teapot"),
    /**
     * @deprecated
     */
    @Deprecated
    INSUFFICIENT_SPACE_ON_RESOURCE(4019, "Insufficient Space On Resource"),
    /**
     * @deprecated
     */
    @Deprecated
    METHOD_FAILURE(4020, "Method Failure"),
    /**
     * @deprecated
     */
    @Deprecated
    DESTINATION_LOCKED(4021, "Destination Locked"),
    UNPROCESSABLE_ENTITY(4022, "Unprocessable Entity"),
    LOCKED(4023, "Locked"),
    FAILED_DEPENDENCY(4024, "Failed Dependency"),
    TOO_EARLY(4025, "Too Early"),
    UPGRADE_REQUIRED(4026, "Upgrade Required"),
    PRECONDITION_REQUIRED(4028, "Precondition Required"),
    TOO_MANY_REQUESTS(4029, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(4031, "Request Header Fields Too Large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(4051, "Unavailable For Legal Reasons"),
    ACCESS_TOKEN_WRONGFUL(4101, "非法的accessToken"),
    ACCESS_TOKEN_EXPIRE(4102, "accessToken已过期"),
    NO_PERMISSION(4103, "没有权限"),
    REQUEST_PARAM_ERROR(4104, "参数错误"),
    DATA_NOT_EXISTS(4105, "数据不存在"),
    USERNAME_OR_PASSWORD_ERROR(4106, "用户名或密码错误"),
    PHONE_CODE_EXPIRE(4107, "手机验证码已过期"),
    PHONE_CODE_ERROR(4108, "请正确输入验证码"),
    ACCESS_TOKEN_NOT_EXISTS(4109, "token不存在"),
    ORDER_DETAIL_EMPTY(4110, "订单详情为空"),
    AUTHENTICATION(4111, "认证失败"),
    COUPON_IS_INVALID(4112, "优惠券已失效"),
    COUPON_IS_USED(4113, "优惠券已使用"),
    COUPON_CONDITION_DISS(4114, "优惠条件不满足"),
    COUPON_DRAW_TIME_NOT(4115, "未到券的领取时间"),
    COUPON_DRAW_TIME_PASSED(4116, "券领取时间已过"),
    COUPON_DRAW_TIMES(4117, "已达到券领取次数"),
    COUPON_IS_NOT_CURRENT_USER(4118, "券不是当前用户的"),
    COUPON_USED_TIME_NOT(4119, "未到券的使用时间"),
    COUPON_USED_TIME_PASSED(4120, "券的使用时间已过"),
    COUPON_MAX_COUNT(4121, "券已发放完毕"),
    COMMODITY_STOCK_LESS(4122, "商品库存不足"),
    PHONE_EMPTY(4123, "请输入手机号"),
    USER_ALREADY_EXIST(4125, "此用户已存在"),
    INVALID_TOKEN(4126, "无效的Token"),
    PASSWORD_EMPTY(4127, "请输入密码"),
    REGISTER_FAIL(4128, "注册失败，请重新尝试"),
    PHONE_CODE_EMPTY(4129, "请输入短信验证码"),
    PHONE_OR_USERNAME_EMPTY(4130, "请输入用户名或手机号"),
    PHONE_ERROR(4131, "请正确输入11位手机号"),
    DISABLED_USER(4132, "用户被禁用"),
    PASSWORD_ERROR(4133, "请输入6-32位密码，可以是大小写字母数字特殊符号中的一种或多种"),
    USERNAME_EMPTY(4135, "请输入用户名"),
    USERNAME_TOO_LONG(4136, "输入的用户名过长"),
    USERNAME_ALL_NUMBER(4137, "用户名不能只包含数字"),
    REALNAME_EMPTY(4138, "请输入姓名"),
    REALNAME_TOO_LONG(4139, "输入的姓名过长"),
    PLATFORM_EMPTY(4140, "请选择平台"),
    ACCOUNT_CATEGORY_EMPTY(4141, "请选择账号分类"),
    PLATFORM_ACCOUNT_EXIST(4142, "一个平台下不能有重复的账号"),
    TOKEN_EMPTY(4143, "token为必传参数"),
    ID_EMPTY(4144, "ID为必传参数"),
    BATCH_CHOOSE_ACCOUNT(4145, "请选择账号"),
    SIGN_NOT_EMPTY(4146, "sign不能为空"),
    SIGN_TYPE_NOT_EMPTY(4147, "signType不能为空"),
    NONCE_STR_NOT_EMPTY(4148, "nonceStr不能为空"),
    USERINFO_EMPTY(4149, "用户信息不存在"),
    SIGN_VALID_FAIL(4150, "用户验签失败"),
    REQUEST_PARAM_TYPE_ERROR(4151, "参数类型错误"),

    DETECT_USER_KEY_EXCEPTION(4200, "用户key校验失败"),
    DETECT_RESULT_FAIL(4201, "用户检测失败"),
    OPENID_EMPTY(4202, "openId为空,或用户未绑定公众号"),
    TEMPLATE_SEND_FAIL(4203, "模板消息发送失败"),
    PHONE_CODE_RISK(4204, "当前账号存在安全风险暂无法获取验证码"),
    DETECT_REQUEST_EXCEPTION(4205, "用户请求校验为空"),


    SVC_CARD_ACCOUNT_DISABLE(4301, "卡被禁用"),
    SVC_CARD_ACCOUNT_CANCELL(4302, "卡已销卡"),
    SVC_CARD_ACCOUNT_REPORT_LOSS(4303, "卡已挂失"),
    SVC_CARD_STATUS_FAIL(4304, "卡状态更新失败"),
    SVC_CARD_ORDERPAY_EXIST(4307, "卡流水记录已存在"),
    SVC_CARD_UPGRADE_FAIL(4310, "升卡失败"),
    SVC_CARD_DOWN_FAIL(4311, "降卡失败"),
    DEPOSIT_CARD_EXPIRE(4312, "卡已过期"),
    DEPOSIT_CARD_USE_ORDER_EXIST(4313, "使用卡订单不存在"),
    DEPOSIT_CARD_EXIST(4314, "卡不存在"),
    DEPOSIT_CARD_FLOW_EXIST(4315, "卡流水记录不存在"),
    DEPOSIT_CARD_AMOUNT_FAIL(4316, "卡额度操作失败"),
    DEPOSIT_CARD_USER_FAIL(4317, "使用押金卡用户不是获取押金卡用户"),
    DEPOSIT_CARD_USER_RISK_FAIL(4318, "用户发放押金卡触发风控"),
    COMMODITY_TEMPLATE_NOT_FOUND(4319, "商品模板不存在"),
    DEPOSIT_CARD_EXISTS(4320, "卡已存在"),
    DEPOSIT_CARD_USERID_EMPTY(4321, "请输入用户id"),
    DEPOSIT_CARD_TEMPLATE_OFF(4322, "押金卡模板已关闭"),
    DEPOSIT_CARD_AMOUNT_ENOUGH(4323, "卡额度不足"),

    WHITE_LIST_EMPTY(4324, "用户不在白名单"),
    USER_IN_WHITE_LIST_EMPTY(4325, "用户已在白名单"),
    CARD_CONFIG_EMPTY(4326, "额度配置为空"),
    NOT_EXIST_MONEY_CONFIG(4327, "暂无开通资格"),
    NOT_EXIST_CARD_RECORD(4328, "会员额度开通记录为空"),
    NOT_EXIST_CARD_PROMPT(4329, "额度提示语为空"),

    RISK_CONFIG_EMPTY(4330, "风控配置不存在"),
    RISK_RULES_EMPTY(4331, "风控规则不存在"),
    RISK_BLACK_LIST_EMPTY(4332, "用户不在黑名单"),
    RISK_BLACK_LIST_EXISTS(4333, "用户已在黑名单"),
    RISK_BLACK_USER_NOT_CERTIFY(4334, "用户未实名认证，请前往用户封禁操作"),

    CREDIT_OPEN_CARD_FAILED(4400, "开通会员失败"),
    CREDIT_UP_MEMBER(4401, "升级会员失败"),
    CREDIT_UNFREEZE_SUCCESS(4402, "此订单已解冻"),
    CREDIT_FREEZE_FAIL(4403, "此订单冻结失败"),
    CREDIT_STATUS_ERROR(4404, "订单状态错误"),
    CREDIT_ORDER_NOT_EXIST(4405, "无支付成功的订单"),
    CREDIT_TRANSFER_OVER(4406, "退款失败,订单金额异常"),
    CREDIT_TRANSFER_EXIST(4407, "此提现订单已存在"),
    WHITE_LIST_NOT_EXIST(4408, "综合评估未通过，建议您在悠悠有品多进行饰品租赁按时归还后再尝试开通固定额度"),
    USER_NOT_CERTIFY(4409, "未实名认证"),
    CARD_DISABLE_OR_CANCELL(4410, "已提现或已挂失"),
    ACCOUNT_UNUSABLE(4411, "您的悠悠有品账号已被限制使用，暂无法使用固定额度相关服务，详情可联系客服咨询"),
    HAS_SERVICE_ORDER(4412, "您有使用中的固额记录，请在订单完成后再试"),
    HAS_WAIT_ORDERS(4413, "您有待支付的固额记录，请在完成守约后再试"),
    USERS_EXISTED(4414, "您已在其他账号开通过固定额度，无法重复开通"),
    TEMP_SERVICE_ORDER(4415, "您有使用中的临额记录，请在订单完成后再试"),
    TEMP_WAIT_ORDERS(4416, "您有待支付的临额记录，请在完成守约后再试"),
    FIXED_AMOUNT_LIMIT(4417, "免押额度不能大于开通费用的5倍"),
    FIXED_VIRTUAL_AMOUNT_EXIST(4418, "已存在此免押额度的配置"),
    FIXED_CARD_OPENED(4419, "此用户已开通固定额度"),
    FIXED_CARD_DOWN(4420, "您的固定额度已提现"),
    REFUND_ORDER_FAIL(4421, "退款失败"),
    FIXED_CARD_HAD_OPENED(4422, "你已开通固定额度"),
    FAILED_THE_EVALUATION(4423, "综合评估未通过，您在悠悠有品中有待扣款或未归还的租赁订单，请在订单完成后再进行激活"),
    TEMP_CARD_IS_ACTIVE(4424, "您已在其他账号激活临时额度，无法重复激活"),
    TEMP_USER_NOT_CERTIFY(4425, "临时额度仅实名用户可用，请先完成实名认证"),
    TEMP_IS_DISABLED(4426, "您已被限制使用临时额度功能，请联系客服处理"),
    PURCHASE_NUM_MAX(4427, "求购数量已达最大值"),
    PURCHASE_NUM_UPDATE_FAILED(4428, "求购商品数量修改失败"),
    PURCHASE_NUM_MIN(4429, "求购数量已达最小值"),
    PURCHASE_PRICE_MIN(4430, "求购价格不能低于最小价格"),
    USER_STEAM_INFO_EMPTY(4431, "系统检测到您Steam绑定信息未设置完成，暂无法交易"),
    COMMODITY_PURCHASE_EXIST(4432, "您已发布过该商品的求购，无法重复发布"),
    PURCHASE_UNIT_SECTION(4433, "求购价格不满足最小单位"),
    COMMODITY_PURCHASE_NOT_EXIST(4434, "求购信息不存在"),
    PURCHASE_PRICE_MAX(4435, "您已是最高求购价，无需置顶"),
    PURCHASE_LACK_OF_BALANCE(4436, "账户余额不足，无法置顶"),
    OPERATING_FREQUENCY(4437, "操作频繁，请稍后再试"),
    PURCHASEMONEY_NOT_ENOUGH(4438, "账户余额不足，请充值"),
    PURCHASE_CHANGE(4439, "求购信息发生变化，请返回供应列表刷新或同步库存后重新供应"),
    PURCHASE_SWITCH_OFF(4440,"此商品暂不支持官方补贴求购，请选择其它商品"),
    PURCHASE_BALANCE_NOT_ENOUGH(4441,"发布账户余额不足"),
    PURCHASE_BALANCE_FROZEN_ASSETS(4442,"冻结求购余额失败，请稍后重试"),
    PURCHASE_BALANCE_UNBLOCKED_ASSETS(4443,"解冻求购余额失败，请稍后重试"),
    PURCHASE_SURPLUS_NUM_CANT_ZERO(4444,"求购剩余数量不为0，并且为终止状态"),
    PURCHASE_STATUS_STOPPED(4445,"求购状态已终止"),

    ORDER_PAY_STATUS_ERROR(4500, "不支持此支付单状态修改"),

    INTERNAL_SERVER_ERROR(5000, "Internal Server Error"),
    NOT_IMPLEMENTED(5001, "Not Implemented"),
    BAD_GATEWAY(5002, "Bad Gateway"),
    SERVICE_UNAVAILABLE(5003, "Service Unavailable"),
    GATEWAY_TIMEOUT(5004, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(5005, "HTTP Version not supported"),
    VARIANT_ALSO_NEGOTIATES(5006, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(5007, "Insufficient Storage"),
    LOOP_DETECTED(5008, "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(5009, "Bandwidth Limit Exceeded"),
    NOT_EXTENDED(5010, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(5011, "Network Authentication Required"),
    SYSTEM(5100, "系统繁忙,请稍后再试"),
    UNKNOWN(5101, "未知异常"),
    REQUEST_PARSE_ERROR(5102, "request解析错误"),
    REFLECT_ERROR(5103, "反射异常"),
    JACKSON_EXCEPTION(5104, "Jackson异常"),
    MAP_TO_XML_EXCEPTION(5105, "MAP 转 XML 异常"),
    XML_TO_MAP_EXCEPTION(5106, "XML 转 MAP 异常"),
    WX_SIGN_VALID_FAIL(5107, "微信验签失败"),
    NOT_SUPPORT(5108, "不支持此接口"),
    DATABASE(5109, "数据库错误"),
    CRAWLER_EXCEPTION(5110, "爬虫异常"),
    CHECK_EXCEPTION(5111, "校验异常"),
    OPENFEIGN_CLIENT_EXCEPTION(5112, "openfeign内部服务异常"),

    ACCESS_TOKEN_SECRET_EMPTY(5200, "token初始化秘钥为空"),

    SYSTEM_TO_THIRD(6000, "系统对接第三方系统错误"),
    WX_ERROR(6001, "微信异常"),
    ALIYUN_OSS_EXCEPTION(6002, "阿里云oss异常"),
    ALIYUN_VOD_EXCEPTION(6003, "阿里云视频点播异常"),
    ALI_SIGN_EXCEPTION(6004, "阿里签名异常"),
    URL_ENCODER_EXCEPTION(6005, "url编码异常"),

    WX_SIGN_CREATE_FAIL(6100, "微信签名创建失败"),

    ALIPAY_CREDIT_FREEZE(6200, "支付宝-预授权冻结失败"),
    ALIPAY_CREDIT_UNFREEZE(6201, "支付宝-预授权解冻失败"),
    ALIPAY_TRANSFER_FAILED(6202, "支付宝-转账失败"),
    ALIPAY_UNFREEZE_SUCCESS(6203, "支付宝-预授权解冻成功"),
    ALIPAY_REFUND_FAILED(6204, "支付宝-退款失败"),

    THIRD_TO_SYSTEM(7000, "第三方系统对接系统错误"),
    VIP_ALREADY_EXIST(7101, "会员已生效，请勿重复操作"),
    VIP_NOT_FOUND(7102, "会员不存在，请先开通会员"),
    VIP_CALLBACK_TYPE_EXCEPTION(7103, "回调类型异常"),
    VIP_CALLBACK_ORDER_EXCEPTION(7104, "回调订单处理异常"),
    BUSINESS_NOT_FOUND(7105, "商户信息不存在"),
    VIP_INFO_NOT_FOUND(7106, "会员信息查无结果"),
    NO_SUCH_RESULTS(7107, "查无结果"),
    REQUEST_PARAMS_INVALID(7108, "查询请求参数非法"),
    VIP_AMOUNT_INVALID(7109, "开通会员金额参数非法"),
    OVERDUE_ORDERPAY_EXIST(7110, "存在逾期中支付单"),
    CALLBACK_INFORM_EXCEPTION(7111, "回调通知商户异常"),
    CREATE_MEMBER_CARD_DISABLED(7112, "您已被限制使用会员功能，请联系客服处理"),
    COMMODITY_NOT_FOUND(7113, "上架商品找不到"),
    COMMODITY_LEASECONFIG_NOT_FOUND(7114, "上架商品租赁配置找不到"),
    COMMODITY_LEASEGIVECONFIG_NOT_FOUND(7115, "上架商品租送活动配置不存在"),

    RULE_CONFIG_ALREADY_EXIST(7500,"规则配置已存在"),




    // region 原DoNet状态码
    DONET_OK(100000,"请求成功"),
    DONET_TOO_MANY_REQUEST(100429,"请求太过频繁;"),

    DONET_UNKNOWN_ERROR(99999,"系统繁忙"),
    DONET_UNSUPPORTED_API_VERSION(99998,"不支持的接口版本"),
    DONET_ACTIVITY_UN_START_OR_END(104001,"活动未开始或已结束"),
    DONET_STORE_CLEAR(103003,"没有库存"),
    DONET_STEAM_PRIVATE(103002,"Steam库存未公开"),
    DONET_EMPTY(103001,"没有数据"),
    DONET_UNAUTHORIZED(102009,"无权访问"),
    DONET_USER_NO_TRADE(102008,"用户不能交易"),
    DONET_NO_STEAM_TREAD_URL(102007,"未设置交易链接"),
    DONET_PWD_BAD(102006,"密码不正确"),
    DONET_UN_BIND_STEAM_ID(102005,"未绑定SteamId"),
    DONET_PWD_NON_EXISTS(102004,"登录密码不存在"),
    DONET_ACC_NON_EXISTS(102003,"帐户不存在"),
    DONET_UN_LOGIN(102002,"未登录"),
    DONET_TOKEN_EXPIRE(102001,"登录过期"),
    DONET_LEASE_RETURN_OFFER_FAIL(101007,"归还报价失败"),
    DONET_LEASE_OUT_OFFER_FAIL(101006,"归还报价失败"),
    DONET_TIMEOUT(101005,"操作超时"),
    DONET_LOCK_FAIL(101004,"获取锁失败"),
    DONET_FREQUENT(101003,"刷新频繁"),
    DONET_SIGN_FAIL(101002,"签名校验失败"),
    DONET_FAIL(101001,"操作失败"),
    // endregion

    //svc卡异常CODE
    SVC_INFO_REPEAT_CLAIM(20001,"不可重复领取"),
    SVC_INFO_REPEAT_FAIL(20002,"红包领取失败"),
    SVC_INFO_USE_FAIL(20003,"红包使用失败"),
    SVC_INFO_USE_FAIL_BY_AMOUNT(20004,"红包余额不足，使用失败"),
    ;

    private final Integer code;
    private final String msg;

    private static final int SUCCESS = 2000;
    public static final int SUCCESS_ZERO = 0;
    private static final int WEIGHT = 80000;
    private static final int DONET_WEIGHT = 100000;
    private static final String DONET_STATUS_PREFIX = "DONET_";

    private static MessageSource messageSource;

    Status(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        String msg = null;
        try {
            msg = messageSource.getMessage("status." + this.code, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            msg = this.msg;
        }
        return msg;
    }

    public Integer getCode() {
        if (this.code == SUCCESS){
            return SUCCESS_ZERO;
        }else {
            if (this.name().startsWith(DONET_STATUS_PREFIX)){
                return Math.subtractExact(this.code,DONET_WEIGHT);
            }else {
                return Math.addExact(this.code,WEIGHT);
            }
        }
    }

    public Integer getOriginCode() {
        return this.code;
    }

    @Component
    public static class Builder {
        @Autowired
        public void setMessageResource(MessageSource messageSource) {
            Status.messageSource = messageSource;
        }
    }

    public static Status getByCode(Integer code){
        if(null == code){
            return Status.UNKNOWN;
        }
        for (Status status : Status.values()){
            if(status.getCode().equals(code)){
                return status;
            }
        }
        return Status.UNKNOWN;
    }
}
