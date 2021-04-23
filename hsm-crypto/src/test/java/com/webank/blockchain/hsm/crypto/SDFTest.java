package com.webank.blockchain.hsm.crypto;

import com.webank.blockchain.hsm.crypto.sdf.AlgorithmType;
import com.webank.blockchain.hsm.crypto.sdf.SDF;
import com.webank.blockchain.hsm.crypto.sdf.SDFCryptoResult;
import com.webank.blockchain.hsm.crypto.utils.Hex;
import org.junit.Assert;
import org.junit.Test;

public class SDFTest {
    @Test
    public void hashTest() {
        byte[] message =
                new byte[] {
                    0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61,
                    0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62,
                    0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63,
                    0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64,
                    0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64, 0x61, 0x62, 0x63, 0x64
                };
        byte[] stdResult =
                new byte[] {
                    (byte) 0xde,
                    (byte) 0xbe,
                    (byte) 0x9f,
                    (byte) 0xf9,
                    0x22,
                    0x75,
                    (byte) 0xb8,
                    (byte) 0xa1,
                    0x38,
                    0x60,
                    0x48,
                    (byte) 0x89,
                    (byte) 0xc1,
                    (byte) 0x8e,
                    0x5a,
                    0x4d,
                    0x6f,
                    (byte) 0xdb,
                    0x70,
                    (byte) 0xe5,
                    0x38,
                    0x7e,
                    0x57,
                    0x65,
                    0x29,
                    0x3d,
                    (byte) 0xcb,
                    (byte) 0xa3,
                    (byte) 0x9c,
                    0x0c,
                    0x57,
                    0x32
                };

        System.out.println("*********Hash*********");
        SDFCryptoResult hashResult = SDF.Hash(null, AlgorithmType.SM3, Hex.toHexString(message));
        Assert.assertNull(hashResult.getSdfErrorMessage());
        Assert.assertEquals(hashResult.getHash(), Hex.toHexString(stdResult));

        System.out.println("*********KeyGen*********");
        SDFCryptoResult keGenResult = SDF.KeyGen(AlgorithmType.SM2);
        Assert.assertNull(keGenResult.getSdfErrorMessage());

        System.out.println("*********Sign*********");
        SDFCryptoResult signResult =
                SDF.Sign(keGenResult.getPrivateKey(), AlgorithmType.SM2, hashResult.getHash());
        Assert.assertNull(signResult.getSdfErrorMessage());

        System.out.println("*********Verify*********");
        SDFCryptoResult verifyResult =
                SDF.Verify(
                        keGenResult.getPublicKey(),
                        AlgorithmType.SM2,
                        hashResult.getHash(),
                        signResult.getSignature());
        Assert.assertNull(verifyResult.getSdfErrorMessage());
        Assert.assertEquals(verifyResult.getResult(), true);

        System.out.println("*********SignWithInnerKey*********");
        SDFCryptoResult signInResult =
                SDF.SignWithInternalKey(1, "123456", AlgorithmType.SM2, hashResult.getHash());
        Assert.assertNull(signInResult.getSdfErrorMessage());

        System.out.println("*********VerifyWithInnerKey*********");
        SDFCryptoResult verifyInResult =
                SDF.VerifyWithInternalKey(
                        1, AlgorithmType.SM2, hashResult.getHash(), signInResult.getSignature());
        Assert.assertNull(verifyInResult.getSdfErrorMessage());
        Assert.assertEquals(verifyInResult.getResult(), true);

        System.out.println("*********GetInternalPublicKey*********");
        SDFCryptoResult exportKeyResult = SDF.ExportInternalPublicKey(1, AlgorithmType.SM2);
        Assert.assertNull(verifyInResult.getSdfErrorMessage());
        if (exportKeyResult.getSdfErrorMessage() != null) {
            System.out.println(exportKeyResult.getSdfErrorMessage());
        } else {
            System.out.println(exportKeyResult.getPublicKey());
        }
    }
}
