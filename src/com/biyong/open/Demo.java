package com.biyong.open;

import java.util.HashMap;
import java.util.Map;

public class Demo {
  public static void main(String[] args) {
    HttpClient client = new HttpClient(
        // 您的私钥 (Base64-RFC4648)
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCc33nwU6KjmiQJN328AM6W0Cw7DPtAF5NL4J704mSfQTqv8AfKyGFMoIFeMzs21sBsdYGpIhDVTEfY3hAVpeeEn1Fu0aSXzq7ZfdGtJuFe4xx8zhp/eIeJaqeXkQ3aRU8nx02m9ctexliJgFTZZxRiAQRv+yLxYpTTC9OlW9oYt3HRuhv3y2RqiaRJPEZMN5meUzoz3UPvmLvgvkxoWDm96GYQnxEOsIkV6MDF7jCegsA3iHvxkprLtGRs0cpt1OgdSPuq0gaAma1D0dTTbesZ0j8gq44quyNP+d1OjYXRhl/FbSdNl/XbmdsS8ir56zYCu9a3mQ7AgRyBPGsM+OBJAgMBAAECggEAEaLuSp6cglgqBP9AG4rbrwjocBsdhC8iWD2AoXdyaJUky/LobVvp1HuXD8giuB+cspTjZuh1jAVvbmpI1KY6SSbAOP1Iy+hor/r9P8JHRCu0YDOrtvkOA90ByoB8VjXEmGxHxqWLn7Qc8BiFcYutNvd2UECjxQ+YO8HwoX6aHn0Th6UdwOXzgdTpC6UlUb7f4FkohcJjWrZUANUfNTyoAqTT9v1tAwkPtraSBEEyB0SQoY/SoIk+fYL7RpWWos3HoHnHA0wLttEjKbUlEbt1Opf5hKvu0HNRJPwqHQ7WiWxsn/7VykTdYXT9++JVPogE3CuPw7/1YjALMeKQx4jHIQKBgQDuj8bE68LaCpoBjo+IydK4nsunwOX3kPjZUJ3xn6qeED1f9bjPu0XIBrLhRrlilIn08XO/4Y8roXpqe0Vqm8qLeasQYIAOXc3D+JR6zpHRMcjuBkmV9Lg5yUJlWf+YYbFTIy3Snn9k2jvhX2Q7vOlsYew0fxZgPpHd1viaTNArMwKBgQCoVw2O/FbFlmbHraxtxhH9p6iecFz+FgTUI2fJ4qwJN+xkengLYsWNFsZi34vdlqr4FxcXOHX8sG3tYYkip+pP6eRCq8cGNlzv3baVioojs+CXiwtnPf+Fc1piO2acQ4itsj+vicHbACw9fizjQYymLo4tOVqdwWYGLCZCXN+mkwKBgQCjlhvrNO/srJBmuOdcGWlSCDIG2pPXa6X4/vsPkTliIii0AL+2UujK/Sz0pZbCLrFbMab50SZn+NcE5Xr4W9RMSCYxmLq3H0PWOkM24yfCl6z863rSRXl+xq+s6EYIrBT4uYrWfrFE6xFcvhjxjd1S4OMkLHGflchYouzeggXNWQKBgHEj6RHz2SH9fuCvgldZK1hPKc4q+OUwyVgG38fZWATuGCxD/5CvdtTnvLhRRMDOdeQ+KJ/Lq8kEjy1fQ3sOXjYNngONTjuHqBjy67dAIK30RHu10Rk3mxgHOnURNVrLLP6I8bK6JJdbW2CNAABRWwWJ/ra4eIXUqQx2+dPaddqPAoGAeXXiBr9iiponM8Ngi4KkFEaxmSFMI9W4zOKXQtPMHgZaQ/7gVfdeQPx1RAzzV/qJy+vf+gj6x84mgpc0nUPq1Prq8ETvLvoqzcwe1Ar7LtIyUunKiUlngJrBmqpy4wJiMaLzJGKc2jkmRgwNlYHn0ZarLbieZ+nzM/she7xwZmQ=",
        // BiYong分配给您的AppId
        "e9ba6dc339c75a66d12eec6ee80f7abc",
        // BiYong分配给您的RSA公钥 (Base64-RFC4648)
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnN958FOio5okCTd9vADOltAsOwz7QBeTS+Ce9OJkn0E6r/AHyshhTKCBXjM7NtbAbHWBqSIQ1UxH2N4QFaXnhJ9RbtGkl86u2X3RrSbhXuMcfM4af3iHiWqnl5EN2kVPJ8dNpvXLXsZYiYBU2WcUYgEEb/si8WKU0wvTpVvaGLdx0bob98tkaomkSTxGTDeZnlM6M91D75i74L5MaFg5vehmEJ8RDrCJFejAxe4wnoLAN4h78ZKay7RkbNHKbdToHUj7qtIGgJmtQ9HU023rGdI/IKuOKrsjT/ndTo2F0YZfxW0nTZf125nbEvIq+es2ArvWt5kOwIEcgTxrDPjgSQIDAQAB",
        // BiYong测试环境
        "http://test10.btcchat.io/mcht/",
        // RSA签名散列算法
        "SHA256",
        // AES加密模式(设置为null不使用AES加密。正式环境采用https通信，非隐私数据接口建议关闭AES加密)
        null
    );
    // 获取用户信息
    Map<String, String> params = new HashMap<>();
    params.put("openId", "d6485b5739e955ab1f6d9628216642ab");
    System.out.println(client.call("biyong-user/info", params));
  }
}
