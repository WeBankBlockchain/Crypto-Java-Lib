package com.webank.wedpr.crypto;

import com.webank.wedpr.crypto.utils.EnvironmentUtils;
import com.webank.wedpr.crypto.utils.NativeUtils;
import java.io.IOException;

/**
 * Native interface for signature
 *
 * @author aaronchu @Description
 * @data 2020/06/18
 */
public class NativeInterface {

    private static final String LIB_FFI_RESOURCE_PATH;
    private static final String LIB_FFI_NAME = "WeDPR_dynamic_lib/libffi_java_sdk";

    static {
        String os = System.getProperty("os.name");
        String tail = EnvironmentUtils.getResourceTailByOs(os);
        LIB_FFI_RESOURCE_PATH = LIB_FFI_NAME + tail;
        try {
            NativeUtils.loadLibrary(LIB_FFI_RESOURCE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load library", e);
        }
    }

    public static native CryptoResult secp256k1EciesEncrypt(String pubKey, String plaintext);

    public static native CryptoResult secp256k1EciesDecrypt(String priKey, String ciphertext);

    public static native CryptoResult secp256k1GenKeyPair();

    public static native CryptoResult secp256k1DerivePublicKey(String priKey);

    public static native CryptoResult keccak256Hash(String message);

    public static native CryptoResult secp256k1Sign(String priKey, String messageHash);

    public static native CryptoResult secp256k1Verify(
            String pubKey, String message, String signature);

    public static native CryptoResult secp256k1RecoverPublicKey(String message, String signature);

    public static native CryptoResult sm3Hash(String message);

    public static native CryptoResult sm2GenKeyPair();

    public static native CryptoResult sm2DerivePublicKey(String priKey);

    public static native CryptoResult sm2Sign(String priKey, String messageHash);

    public static native CryptoResult sm2SignFast(String priKey, String pubKey, String messageHash);

    public static native CryptoResult sm2Verify(String pubKey, String message, String signature);

    public static native CryptoResult sm2ComputeHashE(String pubKey, String sm2ComputeE);

    private static String resolveLibTail(String os) {
        os = os.toLowerCase();
        String tail;
        if (os.contains("windows")) {
            tail = ".dll";
        } else if (os.contains("linux")) {
            tail = ".so";
        } else if (os.contains("mac")) {
            tail = ".dylib";
        } else {
            throw new RuntimeException("Unsupported os: " + os);
        }
        return tail;
    }

    private NativeInterface() {}
}
