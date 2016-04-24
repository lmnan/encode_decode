package com.qianfeng.encode;

import android.util.Base64;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by wyz on 16/4/20.
 */
public class RSAUtils {


    /**
     *
     * @param mod 系数
     * @param publicKey  公钥
     * @param content 要加密的内容
     * @return
     */
    public static String encode(String mod,String publicKey,String content){


        String result = null;
        try {
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new BigInteger(mod), new BigInteger(publicKey));
            PublicKey key = rsa.generatePublic(keySpec);


            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] bytes = cipher.doFinal(content.getBytes());
            result = Base64.encodeToString(bytes, Base64.DEFAULT);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return result;
    }

    /**
     *
     * @param mod  系数
     * @param privateKey 私钥
     * @param content 解密的内容
     * @return
     */
    public static String deocde(String mod,String privateKey,String content){
        String result = null;
        try {
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(new BigInteger(mod), new BigInteger(privateKey));
            PrivateKey key = rsa.generatePrivate(keySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] bytes = cipher.doFinal(Base64.decode(content, Base64.DEFAULT));
            result = new String(bytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return result;
    }

}

