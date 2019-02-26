package tech.aistar.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/2/26 0026.
 */
@Component
@ConfigurationProperties(prefix = "pay.alipay")
public class AlipayProperties {
    /** 支付宝gatewayUrl */
    private String gatewayUrl;
    /** 商户应用id */
    private String appid;
    /** RSA私钥，用于对商户请求报文加签 */
    private String appPrivateKey;
    /** 支付宝RSA公钥，用于验签支付宝应答 */
    private String alipayPublicKey;
    /** 签名类型 */
    private String signType = "RSA2";

    /** 格式 */
    private String formate = "json";
    /** 编码 */
    private String charset = "UTF-8";

    /** 同步地址 */
    private String returnUrl;

    /** 异步地址 */
    private String notifyUrl;

    /** 最大查询次数 */
    private static int maxQueryRetry = 5;
    /** 查询间隔（毫秒） */
    private static long queryDuration = 5000;
    /** 最大撤销次数 */
    private static int maxCancelRetry = 3;
    /** 撤销间隔（毫秒） */
    private static long cancelDuration = 3000;

    private AlipayProperties() {}

    public String description() {
        StringBuilder sb = new StringBuilder("\nConfigs{");
        sb.append("支付宝网关: ").append(gatewayUrl).append("\n");
        sb.append(", appid: ").append(appid).append("\n");
        sb.append(", 商户RSA私钥: ").append(getKeyDescription(appPrivateKey)).append("\n");
        sb.append(", 支付宝RSA公钥: ").append(getKeyDescription(alipayPublicKey)).append("\n");
        sb.append(", 签名类型: ").append(signType).append("\n");

        sb.append(", 查询重试次数: ").append(maxQueryRetry).append("\n");
        sb.append(", 查询间隔(毫秒): ").append(queryDuration).append("\n");
        sb.append(", 撤销尝试次数: ").append(maxCancelRetry).append("\n");
        sb.append(", 撤销重试间隔(毫秒): ").append(cancelDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getKeyDescription(String key) {
        int showLength = 6;
        if (null!=key && key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength)).append("******")
                    .append(key.substring(key.length() - showLength)).toString();
        }
        return null;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        System.out.println("===");
        this.appid = appid;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getFormate() {
        return formate;
    }

    public void setFormate(String formate) {
        this.formate = formate;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static void setMaxQueryRetry(int maxQueryRetry) {
        AlipayProperties.maxQueryRetry = maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static void setQueryDuration(long queryDuration) {
        AlipayProperties.queryDuration = queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static void setMaxCancelRetry(int maxCancelRetry) {
        AlipayProperties.maxCancelRetry = maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }

    public static void setCancelDuration(long cancelDuration) {
        AlipayProperties.cancelDuration = cancelDuration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayProperties{");
        sb.append("gatewayUrl='").append(gatewayUrl).append('\'');
        sb.append(", appid='").append(appid).append('\'');
        sb.append(", appPrivateKey='").append(appPrivateKey).append('\'');
        sb.append(", alipayPublicKey='").append(alipayPublicKey).append('\'');
        sb.append(", signType='").append(signType).append('\'');
        sb.append(", formate='").append(formate).append('\'');
        sb.append(", charset='").append(charset).append('\'');
        sb.append(", returnUrl='").append(returnUrl).append('\'');
        sb.append(", notifyUrl='").append(notifyUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
