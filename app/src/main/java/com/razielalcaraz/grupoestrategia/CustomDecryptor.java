package com.razielalcaraz.grupoestrategia;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

import javax.crypto.spec.IvParameterSpec;

public class CustomDecryptor {
static String TAG="customDecryptor";
    public static byte[] generateKey() throws GeneralSecurityException, UnsupportedEncodingException {
        final String KEY = "b14ca5898a4e4133bbce2ea2315a1acm";
        byte[] binary = KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        binary = sha.digest(binary);
        // Use only first 128 bit.
        binary = Arrays.copyOf(binary, 16);
        return binary;
    }

    public static String encrypt(byte[] key, String value) throws GeneralSecurityException {
        // Argument validation.
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

        byte[] original = value.getBytes(StandardCharsets.UTF_8);
        byte[] binary = cipher.doFinal(original);
        Log.d(TAG, String.valueOf(binary));
        return Base64.encodeToString(binary, Base64.DEFAULT);
    }

    public static String decrypt(byte[] key, String encrypted) throws GeneralSecurityException {
        // Argument validation.
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid key size.");
        }

        // Setup AES tool.
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

        byte[] binary = Base64.decode(encrypted, Base64.DEFAULT);
        byte[] original = cipher.doFinal(binary);
        return new String(original, Charset.forName("UTF-8"));
    }
}
