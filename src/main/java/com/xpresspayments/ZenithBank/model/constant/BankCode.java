package com.xpresspayments.ZenithBank.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kolawole
 */
public class BankCode {

    private static final String FAILED = "FAILED";
    private static final Map<String, String> vendorBankCode = new HashMap<>(4);

    static {
        vendorBankCode.put("001", "001080032");
        vendorBankCode.put("011", "011151003");
        vendorBankCode.put("023", "023150005");
        vendorBankCode.put("030", "030150014");
        vendorBankCode.put("032", "032154568");
        vendorBankCode.put("033", "033152048");
        vendorBankCode.put("035", "035150103");
        vendorBankCode.put("044", "044150291");
        vendorBankCode.put("050", "050150010");
        vendorBankCode.put("057", "057150013");
        vendorBankCode.put("058", "058152052");
        vendorBankCode.put("060002", "060002600");
        vendorBankCode.put("063", "063150162");
        vendorBankCode.put("068", "068150015");
        vendorBankCode.put("070", "070150003");
        vendorBankCode.put("076", "076151365");
        vendorBankCode.put("082", "082150017");
        vendorBankCode.put("090118", "090185090");
        vendorBankCode.put("090121", "090118509");
        vendorBankCode.put("100", "100152049");
        vendorBankCode.put("101", "101152019");
        vendorBankCode.put("214", "214150018");
        vendorBankCode.put("215", "215082334");
        vendorBankCode.put("221", "221159522");
        vendorBankCode.put("232", "232150016");
        vendorBankCode.put("301", "301080020");
        vendorBankCode.put("327", "327155327");
        vendorBankCode.put("502", "502155502");
        vendorBankCode.put("526", "526155261");
        vendorBankCode.put("552", "552155552");
        vendorBankCode.put("559", "559155591");
        vendorBankCode.put("560", "560155560");
        vendorBankCode.put("561", "561155561");
        vendorBankCode.put("601", "601155601");
        vendorBankCode.put("608", "608155608");

    }

    public static Map<String, String> getVendorBankCode() {
        return vendorBankCode;
    }

    public static String getBankSortCode(String key) {
        if (vendorBankCode.get(key) != null) {
            return vendorBankCode.get(key);
        } else {
            return FAILED;
        }
    }
}
