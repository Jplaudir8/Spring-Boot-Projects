package com.udacity.jwdnd.course1.cloudstorage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    public String encryptValue(String data, String key) {
        byte[] encryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decryptValue(String data, String key) {
        byte[] decryptedValue = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.getMessage());
        }

        return new String(decryptedValue);
    }

    /*public String generateKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256, new SecureRandom());
            System.out.println(generator.generateKey().toString());
            return generator.generateKey().toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.print("error in method: generateKey()");
        }
        return null;
    }*/

    public String generateKey() {
        try {
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128); /* 128-bit AES */
            SecretKey secret = gen.generateKey();
            byte[] binary = secret.getEncoded();
            String key = String.format("%032X", new BigInteger(+1, binary));
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
