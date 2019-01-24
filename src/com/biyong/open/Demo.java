package com.biyong.open;

import java.util.HashMap;
import java.util.Map;

public class Demo {
  public static void main(String[] args) {
    HttpClient client = new HttpClient(
        "", // 你的私钥 (Base64-RFC4648)
        "", // BiYong分配给你的AppId
        "", // BiYong分配给你的RSA公钥 (Base64-RFC4648)
        "https://open.biyong.sg/dev-api/", // BiYong商户开发环境API
        "SHA256", // RSA签名散列算法
        null // AES加密模式(设置为null不使用AES加密。正式环境采用https通信，非隐私数据接口建议关闭AES加密)
    );
    // 以上信息登录 https://open.biyong.sg/ 创建应用后在应用信息中查看

    // 调用获取用户信息接口
    Map<String, String> params = new HashMap<>();
    params.put("openId", "");
    System.out.println(client.call("biyong-user/info", params));
    // 其它接口调用参考 https://github.com/openbiyong/biyong-developer/blob/master/BiYong商户后台接口文档.md
  }
}
