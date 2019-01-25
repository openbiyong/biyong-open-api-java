package com.biyong.open;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://github.com/openbiyong/biyong-developer/blob/master/BiYong商户平台通信规范.md">开放平台通信</a> Demo
 * <p></p>
 * <p>需要在<a href="https://open.biyong.sg">BiYong开放平台</a>完成注册。
 * 商户信息审核通过后，先创建开发环境应用进行调试</p>
 * <p></p>
 * <p>将对应的信息填写到下面的参数中</p>
 * <p>{@link Demo#yourPrivateKey yourPrivateKey} 你的私钥(Base64-RFC4648)，在开放平台创建应用时，应填写此私钥对应公钥</p>
 * <p>{@link Demo#yourAppId yourAppId} BiYong分配给你的AppId</p>
 * <p>{@link Demo#yourMerchantPublicKey yourMerchantPublicKey} BiYong分配给你的RSA公钥(Base64-RFC4648)</p>
 * <p>{@link Demo#biyongServerUrl biyongServerUrl} BiYong 开放平台API 已填写开发环境API</p>
 * <p>{@link Demo#rsaSignHashMode rsaSignHashMode} RSA签名散列算法</p>
 * <p>{@link Demo#aesMode aesMode} AES加密模式(设置为null不使用AES加密。正式环境采用https通信，非隐私数据接口建议关闭AES加密)</p>
 * <p></p>
 * <p>填写完毕后运行{@link Demo#main(String[]) main函数}，查看控制台输出信息</p>
 * <p>其它接口调用参考 <a href="https://github.com/openbiyong/biyong-developer/blob/master/BiYong商户后台接口文档.md">开放平台接口文档</a></p>
 */
public class Demo {
  private static final String yourPrivateKey = "";
  private static final String yourAppId = "";
  private static final String yourMerchantPublicKey = "";
  private static final String biyongServerUrl = "https://open.biyong.sg/dev-api/";
  private static final String rsaSignHashMode = "SHA256";
  private static final String aesMode = null;

  public static void main(String[] args) {
    HttpClient client = new HttpClient(
        yourPrivateKey,
        yourAppId,
        yourMerchantPublicKey,
        biyongServerUrl,
        rsaSignHashMode,
        aesMode
    );

    Map<String, String> params = new HashMap<>();
    params.put("message", "Hello, BiYong");
    System.out.println(client.call("/common/test", params));
  }
}
