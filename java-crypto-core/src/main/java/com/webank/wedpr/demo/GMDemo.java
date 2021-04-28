package com.webank.wedpr.demo;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.webank.wedpr.crypto.CryptoResult;
import com.webank.wedpr.crypto.NativeInterface;
import org.bouncycastle.util.encoders.Hex;

public class GMDemo {

    public static void main(String[] args) throws Exception {
        //SM2 encryption demo
        {
            String text = "Hello World";

            CryptoResult keyPair = NativeInterface.sm2GenKeyPair();
            String message = Hex.toHexString(text.getBytes());
            String cipher = NativeInterface.sm2Encrypt(keyPair.getPublicKey(), message).encryptedData;
            System.out.println(text+"-->"+cipher);
            String plainText = NativeInterface.sm2Decrypt(keyPair.privateKey, cipher).decryptedData;

            String recoveredText = new String(Hex.decode(plainText));
            System.out.println(cipher+"-->"+recoveredText);
        }
        //SM2 signature demo
        {
            String text = "Hello World";
            String message = Hex.toHexString(text.getBytes());
            CryptoResult keyPair = NativeInterface.sm2GenKeyPair();
            System.out.println("privteKey = " + keyPair.privateKey);
            System.out.println("publicKey = " + keyPair.publicKey);

            CryptoResult Signature = NativeInterface.sm2Sign(keyPair.privateKey, message);
            System.out.println("gm signature = " + Signature.signature.length());
            CryptoResult resultTrue = NativeInterface.sm2Verify(keyPair.publicKey, message, Signature.signature);
            System.out.println("result = " + resultTrue.booleanResult);
        }
        //SM3 demo
        {
            String text = "Hello World";
            String message = Hex.toHexString(text.getBytes());
            CryptoResult hash =  NativeInterface.sm3Hash(message);
            System.out.println("sm3 hash = " + hash.hash);
        }
        //SM4 demo
        {
            String text = "Hello World";
            String key = "1234567890123456";//Must be 16 length!!
            byte[] iv = new byte[16];//Just for demo, in fact you should use
            String textHex = Hex.toHexString(text.getBytes());
            String keyHex = Hex.toHexString(key.getBytes());
            String ivHex = Hex.toHexString(iv);

            CryptoResult encryptedResult = NativeInterface.sm4Encrypt(textHex, keyHex, ivHex);
            System.out.println(text+"-->"+encryptedResult.encryptedData);

            CryptoResult decryptedResult = NativeInterface.sm4Decrypt(encryptedResult.encryptedData, keyHex, ivHex);
            System.out.println(encryptedResult.encryptedData+"-->"+new String(Hex.decode(decryptedResult.decryptedData)));
        }
    }
}
