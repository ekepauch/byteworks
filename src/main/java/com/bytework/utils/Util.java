package com.bytework.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Util {


    private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    private Cipher cipher;


String encKey = "a0c74ee5987324d9ecd30ce4a728f559";


    public Util() {
    }





    public String encryptData(String data)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String endata = "";
        if ("".equals(data) || null == data) {
            return "";
        }
        byte byteKey[] = (byte[]) hex2byte(encKey);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        Cipher cip = Cipher.getInstance("AES");
        cip.init(1, skeySpec);
        byte bytedata[] = cip.doFinal(data.getBytes("UTF-8"));
        endata = asHex(bytedata);
        return endata;
    }

    public String decryptData(String encrypted)
            throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if ("".equals(encrypted) || null == encrypted) {
            return "";
        }
        byte byteKey[] = (byte[]) hex2byte(encKey);
        byte byteEncData[] = hex2byte(encrypted);
        SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
        Cipher cip = Cipher.getInstance("AES");
        cip.init(2, skeySpec);
        byte original[] = cip.doFinal(byteEncData);
        String originalString = new String(original, "UTF-8");
        return originalString;
    }

    public String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            if ((buf[i] & 0xff) < 16) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString(buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    public static byte[] hex2byte(String s) {
        return hex2byte(s.getBytes(), 0, s.length() >> 1);
    }

    public static byte[] hex2byte(byte b[], int offset, int len) {
        byte d[] = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }
        return d;
    }



    public static String sha256BytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        }
        int len = data.length;
        String str = "";
        for (int i = 0; i < len; i++) {
            if ((data[i] & 0xFF) < 16) {
                str = str + "0" + Integer.toHexString(data[i] & 0xFF);
            } else {
                str = str + Integer.toHexString(data[i] & 0xFF);
            }
        }
        return str;
    }

}
