package com.webank.fisco.bcos.wedpr.sdk;

import com.webank.wedpr.utils.EnvironmentUtils;
import com.webank.wedpr.utils.NativeUtils;

import java.io.IOException;

public class NativeInterface {
    private static final String LIB_FFI_RESOURCE_PATH;
    private static final String LIB_FFI_NAME = "WeDPR_dynamic_lib/libffi_vrf_java_sdk";
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
    public static native SdkResult curve25519VrfProveUtf8(String privateKey, String utf8Message);
    public static native SdkResult curve25519VrfProveFastUtf8(String privateKey, String publicKey, String utf8Message);
    public static native SdkResult curve25519VrfVerifyUtf8(String publicKey, String utf8Message, String proof);
    public static native SdkResult curve25519VrfDerivePublicKey(String privateKey);
    public static native SdkResult curve25519VrfProofToHash(String proof);
    public static native SdkResult curve25519VrfIsValidPublicKey(String publicKey);
}