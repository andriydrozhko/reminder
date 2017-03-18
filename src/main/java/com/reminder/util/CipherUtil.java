package com.reminder.util;


import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public final class CipherUtil {

    private CipherUtil() { }

    public static String generateVerificationToken(String str) {
        return encrypt(str + new Date());
    }

    private static final byte[] SECRET = new byte[] {'z','w','2','y','|','-','"', 'a'};

    public static String encrypt(String str) {
        String s = str;
        try {
            s = encryptToSHA(s);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
    }

    private static String encryptToSHA(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(str.getBytes());

        return HexBin.encode(digest.digest()).toLowerCase();
    }

    public static String encryptDES(String text) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encVal = c.doFinal(text.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    public static String decryptDES(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(SECRET, "DES");
    }

}
