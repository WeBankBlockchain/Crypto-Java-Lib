package com.webank.wedpr.demo;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.webank.wedpr.crypto.CryptoResult;
import com.webank.wedpr.crypto.NativeInterface;
import org.bouncycastle.util.encoders.Hex;

public class GMEncryptDemo {

    public static void main(String[] args) throws Exception {
        //SM2 demo
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
