package com.biyong.open;

import static com.biyong.open.Utils.CHARSET_UTF_8;
import static com.biyong.open.Utils.readInputStream;

import com.biyong.open.Utils.MessageCipher;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClient extends MessageCipher {
  private static final String client = "java-1.0.0";
  private String appId;
  private String biyongServerUrl;

  public HttpClient(
      String yourPrivateKey,
      String yourAppId,
      String yourMerchantPublicKey,
      String biyongServerUrl,
      String rsaSignHashMode,
      String aeeMode) {
    super(yourPrivateKey,
          yourMerchantPublicKey,
          rsaSignHashMode,
          aeeMode);
    this.appId = yourAppId;
    this.biyongServerUrl = biyongServerUrl;
  }

  public String call(String api) {
    return call(api, null);
  }

  public String call(String api, Map<String, String> params) {
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(biyongServerUrl + api).openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty(Utils.Headers.AppId.name(), appId);
      connection.setRequestProperty(Utils.Headers.MerchantClient.name(), client);
      connection.setRequestProperty(Utils.Headers.RsaSignHashMode.name(), rsaSignHashMode);
      connection.setRequestProperty(Utils.Headers.AesEncryptMode.name(), aesMode);
      connection.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      Utils.MerchantRequest r = clientEncrypt(params);
      wr.write(r.getData());
      wr.flush();
      wr.close();
      int responseCode = connection.getResponseCode();
      switch (responseCode) {
        case 200:
          return new String(
              clientDecrypt(readInputStream(connection.getInputStream()), r.aes),
              CHARSET_UTF_8);
        default:
          // 封装了网络异常等错误
          System.out.println(
              new String(readInputStream(connection.getErrorStream()), CHARSET_UTF_8));
          return wrapErrorMessage(responseCode);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String wrapErrorMessage(int responseCode) {
    return "{\"status\":" + responseCode + "," +
           "\"message\":\"请求失败\"," +
           "\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
  }
}
