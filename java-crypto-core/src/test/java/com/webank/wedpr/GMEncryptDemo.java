package com.webank.wedpr;

import com.webank.wedpr.crypto.CryptoResult;
import com.webank.wedpr.crypto.NativeInterface;
import org.bouncycastle.util.encoders.Hex;

public class GMEncryptDemo {

    public static void main(String[] args) throws Exception {
        {
            String text = "Hello World!";

            CryptoResult keyPair = NativeInterface.sm2GenKeyPair();
            String message = Hex.toHexString(text.getBytes());
            String cipher = NativeInterface.sm2Encrypt(keyPair.getPublicKey(), message).encryptedData;
            String plainText = NativeInterface.sm2Decrypt(keyPair.privateKey, cipher).decryptedData;

            String recoveredText = new String(Hex.decode(plainText));
            System.out.println(recoveredText);
        }
    }
}
