package com.webank.wedpr.crypto;

import com.webank.wedpr.crypto.utils.EnvironmentUtils;
import com.webank.wedpr.crypto.utils.NativeUtils;

import java.io.IOException;

public class NativeInterface
{
    private static final String LIB_FFI_RESOURCE_PATH;
    private static final String LIB_FFI_NAME = "WeDPR_dynamic_lib/libffi_java_vrf_binary";

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
    public static native VRFResult curve25519VrfProveUtf8(byte[] privateKeyBytes, byte[] input);
    public static native VRFResult curve25519VrfProveFastUtf8(byte[] privateKeyBytes, byte[] publicKeyBytes, byte[] input);
    public static native VRFResult curve25519VrfVerifyUtf8(byte[] publicKeyBytes, byte[] input, byte[] proof);
    public static  native VRFResult curve25519VrfDerivePublicKey(byte[] privateKeyBytes);
    public static native VRFResult curve25519VrfProofToHash(byte[] proof);
    public static native VRFResult curve25519VrfIsValidPublicKey(byte[] publicKeyBytes);
}