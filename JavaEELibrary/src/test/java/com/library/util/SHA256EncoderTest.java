package com.library.util;

import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.Assert.*;

public class SHA256EncoderTest {

    @Test
    public void encode() {
        final String testLine = "hello world";
        final String expectedHash = "B94D27B9934D3E08A52E52D7DA7DABFAC484EFE37A5380EE9088F7ACE2EFCDE9";
        String actualHash = null;
        try {
            actualHash = SHA256Encoder.encode(testLine);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println("md5Encode test:");
        assertEquals(expectedHash, actualHash);
        System.out.println("Expected: " + expectedHash);
        System.out.println("Actual: " + actualHash);
    }
}