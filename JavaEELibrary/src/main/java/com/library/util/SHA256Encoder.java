package com.library.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Encoder {
    public static String encode(String lineToEncode) throws NoSuchAlgorithmException {
        final StringBuilder stringBuilder = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(lineToEncode.getBytes());
        for (byte b: bytes) {
            stringBuilder.append(String.format("%02X", b));
        }
        return stringBuilder.toString();
    }
}
