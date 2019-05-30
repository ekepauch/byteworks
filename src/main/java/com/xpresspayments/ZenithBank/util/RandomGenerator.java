package com.xpresspayments.ZenithBank.util;

import java.util.Random;

/**
 * @author Abdussamad
 */
public class RandomGenerator {

  private static Random random = new Random();

  public static String generateId() {
    long id = 10000000000000000L;
    id += System.currentTimeMillis();
    id += random.nextInt();
    return String.valueOf(id);
  }
}
