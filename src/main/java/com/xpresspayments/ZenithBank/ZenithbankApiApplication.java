package com.xpresspayments.ZenithBank;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class ZenithbankApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZenithbankApiApplication.class, args);


//    String hexadecimal = "6a95b4dd5419f2ffb9f655309c931cb0";
//    System.out.println("hexadecimal: " + hexadecimal);
//
//    BigInteger bigint = new BigInteger(hexadecimal, 16);
//
//    StringBuilder sb = new StringBuilder();
//    byte[] ba = Base64.encodeInteger(bigint);
//    for (byte b : ba) {
//      sb.append((char)b);
//    }
//    String s = sb.toString();
//    System.out.println("base64: " + s);
//    System.out.println("encoded: " + Base64.isBase64(s));
  }
}
