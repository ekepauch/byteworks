package com.upperlink.fcmb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
// to ignore SSL certificate
// Create a trust manager that does not validate certificate chains
//    SSLContext ctx = null;
//    TrustManager[] trustAllCerts = new X509TrustManager[]{new X509TrustManager(){
//      public X509Certificate[] getAcceptedIssuers(){return null;}
//      public void checkClientTrusted(X509Certificate[] certs, String authType){}
//      public void checkServerTrusted(X509Certificate[] certs, String authType){}
//    }};
//    // Install the all-trusting trust manager
//    try {
//      ctx = SSLContext.getInstance("SSL");
//      ctx.init(null, trustAllCerts, null);
//    } catch (NoSuchAlgorithmException | KeyManagementException e) {
//      log.info("Error loading ssl context {}", e.getMessage());
//    }
//
//    SSLContext.setDefault(ctx);
  }
}
