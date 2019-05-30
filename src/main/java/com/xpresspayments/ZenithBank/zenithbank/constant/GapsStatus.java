package com.xpresspayments.ZenithBank.zenithbank.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdussamad
 */
public class GapsStatus {

    private static Map<String, String> statusCodes = new HashMap<>();

    private static String INTERNAL_ERROR = "INTERNAL ERROR";

    static {
        statusCodes.put("00", "SUCCESSFUL");
        statusCodes.put("01", "FAILED");
        statusCodes.put("1001", "INVALID_USERNAME_OR_PASSWORD");
        statusCodes.put("1002", "ACCESS_DENIED_OR_NOT_ACTIVATED");
        statusCodes.put("1003", "ACCESS_TO_SYSTEM_IS_LOCKED");
        statusCodes.put("1004", "PASSWORD_EXPIRED");
        statusCodes.put("1005", "INVALID_HASH_VALUE");
        statusCodes.put("1006", "INVALID_XML_FORMAT_IN_TRANSACTION_DETAILS");
        statusCodes.put("1007", "TRANSACTION_VALIDATION_ERROR");
        statusCodes.put("1008", "SYSTEM_ERROR");
        statusCodes.put("1010", "FAILED_ONLY_SINGLE_TRANSACTIONS_ALLOWED");
        statusCodes.put("1011", "Date greater than current date");
        statusCodes.put("1012", "Start date cannot be greater than current date");
        statusCodes.put("1013", "No record found based on the parameters supplied");
    }

    public static Map<String, String> getStatusCodes() {
        return statusCodes;
    }

    public static String getStatusMessage(String code) {
        String errorCode = statusCodes.get(code);
        return (errorCode != null) ? errorCode : INTERNAL_ERROR;
    }

}
