package com.webank.wedpr.crypto;

import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.webank.wedpr.utils.EnvironmentUtils;
import com.webank.wedpr.utils.NativeUtils;
import com.webank.wedpr.utils.SM2Util;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Native interface for signature
 * @author aaronchu
 * @Description
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

    public static native CryptoResult secp256k1Verify(String pubKey, String message, String signature);

    public static native CryptoResult secp256k1RecoverPublicKey(String message, String signature);

    public static native CryptoResult sm3Hash(String message);

    public static native CryptoResult sm2GenKeyPair();

    public static native CryptoResult sm2DerivePublicKey(String priKey);

    public static native CryptoResult sm2Sign(String priKey, String messageHash);

    public static native CryptoResult sm2SignFast(String priKey, String pubKey, String messageHash);

    public static native CryptoResult sm2Verify(String pubKey, String message, String signature);
    
    public static native CryptoResult sm2ComputeHashE(String pubKey, String sm2ComputeE);

    public static native CryptoResult curve25519VrfProveUtf8(String privateKey, String utf8Message);

    public static native CryptoResult curve25519VrfProveFastUtf8(String privateKey, String publicKey, String utf8Message);

    public static native CryptoResult curve25519VrfVerifyUtf8(String publicKey, String utf8Message, String proof);

    public static native CryptoResult curve25519VrfDerivePublicKey(String privateKey);

    public static native CryptoResult curve25519VrfProofToHash(String proof);

    public static native CryptoResult curve25519VrfIsValidPublicKey(String publicKey);

    /**
     *
     * @param message Hex for of message
     * @param key Hex format of key bytes, which is 16 bytes length
     * @param iv Hex format of iv bytes,which is 16 bytes length. You can pass 00000000000000000000000000000000， but for security reasons
     *      *          you should use a random-generated 16 bytes. You should keep it as secrets.
     * @return
     */
    public static native CryptoResult sm4Encrypt(String message, String key, String iv);

    /**
     *
     * @param cipher Encrypted data
     * @param key Hex format of key bytes, which is 16 bytes length
     * @param iv Hex format of iv bytes,which is 16 bytes length. Iv is same with sm4Encrypt
     * @return
     */
    public static native CryptoResult sm4Decrypt(String cipher, String key, String iv);

    /**
     * SM2 encrypt
     * @param pubKey Hex format of public key. Should be standard.
     * @param plaintextHex Hex of message bytes. This is not UTF8-encoded string!
     * @return @return CrytoResult check encryptedData as result.
     * @throws Exception
     */
    public static CryptoResult sm2Encrypt(String pubKey, String plaintextHex) throws Exception{
        ECPoint q = SM2Util.curve.decodePoint(Hex.decode(pubKey));
        ECPublicKeyParameters params = new ECPublicKeyParameters(q, SM2Util.domain);

        SM2 sm2 = new SM2();
        sm2.setPublicKeyParams(params);
        byte[] cipher = sm2.encrypt(Hex.decode(plaintextHex), KeyType.PublicKey);
        CryptoResult result = new CryptoResult();
        result.setEncryptedData(Hex.toHexString(cipher));
        return result;
    }

    /**
     *
     * @param priKey Hex plain private key(64 bytes)
     * @param ciphertext Cipher text
     * @return CrytoResult check decryptedData as result, which is hex decoded string(Not utf-8).
     */
    public static CryptoResult sm2Decrypt(String priKey, String ciphertext){
        BigInteger d = new BigInteger(1, Hex.decode(priKey));
        ECPrivateKeyParameters params = new ECPrivateKeyParameters(d, SM2Util.domain);

        SM2 sm2 = new SM2();
        sm2.setPrivateKeyParams(params);
        byte[] plainText = sm2.decrypt(Hex.decode(ciphertext), KeyType.PrivateKey);

        CryptoResult result = new CryptoResult();
        result.setDecryptedData(Hex.toHexString(plainText));
        return result;

    }
    private NativeInterface(){}
}
