package com.webank.wedpr;

import com.webank.wedpr.crypto.CryptoResult;
import com.webank.wedpr.crypto.NativeInterface;

/**
 * @author aaronchu
 * @Description
 * @data 2020/07/15
 */
public class Main {
    public static void main(String[] args) throws Exception{
        {

            CryptoResult keyPair =  NativeInterface.secp256k1GenKeyPair();
            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
            CryptoResult Signature =  NativeInterface.secp256k1Sign(keyPair.privateKey, message);
            System.out.println("signature = " + Signature.signature);
            message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
            CryptoResult result =  NativeInterface.secp256k1Verify(keyPair.publicKey, message, Signature.signature);
            System.out.println("result = " + result.booleanResult);

        }
        {
            CryptoResult keyPair = NativeInterface.sm2GenKeyPair();
            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
            CryptoResult signature = NativeInterface.sm2SignFast(keyPair.privateKey, keyPair.publicKey, message);
            System.out.println(signature.signature);
        }

        {
            CryptoResult keyPair = NativeInterface.sm2GenKeyPair();
            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
            CryptoResult result = NativeInterface.sm2ComputeHashE(keyPair.publicKey,message);
            System.out.println(result.hash);
        }
//
//        {
//            CryptoResult keyPair =  NativeInterface.sm2keyPair();
//            System.out.println("privteKey = " + keyPair.privateKey);
//            System.out.println("publicKey = " + keyPair.publicKey);
//            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
//            CryptoResult Signature =  NativeInterface.sm2Sign(keyPair.privteKey, message);
//            System.out.println("国密signature = " + Signature.signature.length());
//            CryptoResult resultTrue =  NativeInterface.sm2verify(keyPair.publicKey, message, Signature.signature);
//            System.out.println("result = " + resultTrue.result);
//
//            String messageErr = "47adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
//            CryptoResult result =  NativeInterface.sm2verify(keyPair.publicKey, messageErr, Signature.signature);
//            System.out.println("result = " + result.result);
//        }
//
//        {
//            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
//            CryptoResult hash =  NativeInterface.sm3(message);
//            System.out.println("sm3 hash = " + hash.hash);
//        }
//
//        {
//            String message = "847adcf9b24cf0041ddff02ffe324e30b1271c5170086f8ee799dd1123dacb2e";
//            CryptoResult hash =  NativeInterface.keccak256(message);
//            System.out.println("keccak256 hash = " + hash.hash);
//        }
    }
}
