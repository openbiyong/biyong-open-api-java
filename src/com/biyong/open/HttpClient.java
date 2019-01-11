package com.biyong.open;

import static com.biyong.open.Utils.CHARSET_UTF_8;
import static com.biyong.open.Utils.readInputStream;
import static java.util.Optional.ofNullable;

import com.biyong.open.Utils.MessageCipher;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);
      connection.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      Utils.MerchantRequest r = clientEncrypt(toJSONString(params));
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
          InputStream s = ofNullable(connection.getErrorStream()).orElse(connection.getInputStream());
          System.out.println(new String(readInputStream(s), CHARSET_UTF_8));
          return wrapErrorMessage(responseCode);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String toJSONString(Map<String, String> params) {
    String dataString = "";
    if (params != null && params.size() > 0) {
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<String, String> entry : params.entrySet()) {
        if (entry.getKey() != null && entry.getKey().length() > 0 &&
            entry.getValue() != null) {
          sb.append("\"").append(entry.getKey()).append("\":")
            .append("\"").append(entry.getValue()).append("\",");
        }
      }
      if (sb.length() > 0) {
        dataString += sb.toString();
        if (dataString.endsWith(",")) {
          dataString = dataString.substring(0, dataString.length() - 1);
        }
      }
    }
    return "{" + dataString + "}";
  }

  private String wrapErrorMessage(int responseCode) {
    return "{\"status\":" + responseCode + "," +
           "\"message\":\"请求失败\"," +
           "\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
  }
}
