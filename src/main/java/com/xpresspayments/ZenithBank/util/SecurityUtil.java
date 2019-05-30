package com.xpresspayments.ZenithBank.util;



import com.xpresspayments.ZenithBank.exception.ProcessingException;
import org.apache.commons.codec.binary.Hex;

import javax.net.ssl.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * @author Abdussamad
 */
public class SecurityUtil {


  public static String hashWithSha512(String rawKey) throws ProcessingException {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] encrypted = md.digest(rawKey.getBytes());
      return new String(Hex.encodeHex(encrypted));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new ProcessingException(errorMessage);
    }
  }

  public static String get_SHA_512_SecurePassword(String input, String salt){
    String hashedValue = null;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt.getBytes(StandardCharsets.UTF_8));
      byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for(int i=0; i< bytes.length ;i++){
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      hashedValue = sb.toString();
    }
    catch (NoSuchAlgorithmException e){
      e.printStackTrace();
    }
    return hashedValue;
  }

  public static void byPassSSLCertificate() {
    try {
      TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
      }
      };

      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

      HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
          return true;
        }
      };
      HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {

    }
  }

}
