package com.upperlink.fcmb.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author Abdussamad
 */
public class MoneyConverter {

  public static String koboToNairaString(Long koboAmount) {
    BigDecimal amount = BigDecimal.valueOf(koboAmount);
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    df.setMinimumFractionDigits(2);
    df.setGroupingUsed(false);
    amount = amount.divide(BigDecimal.valueOf(100L), RoundingMode.FLOOR);
    return df.format(amount);
  }

  public static Long nairaStringToKoboLong(String nairaString) {
    Double nairaAmount = Double.parseDouble(nairaString);
    Double koboAmount = nairaAmount * 100;
    return koboAmount.longValue();
  }
}
