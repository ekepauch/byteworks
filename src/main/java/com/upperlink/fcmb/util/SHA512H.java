package com.upperlink.fcmb.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Kolawole
 */
public class SHA512H {

    public static String sha512(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("SHA-512");
        byte[] digest = md5.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte aDigest : digest) {
            sb.append(Integer.toHexString((aDigest & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


    public static String sha512hash(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        StringBuilder hashCodeBuffer = new StringBuilder();
        for (byte aByteData : byteData) {
            hashCodeBuffer.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return hashCodeBuffer.toString();
    }


}
