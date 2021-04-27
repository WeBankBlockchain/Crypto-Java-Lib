package com.webank.wedpr.utils;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

//@Slf4j
public class SM2Util {
    private static final BigInteger xg = new BigInteger("32C4AE2C" + "1F198119"
            + "5F990446" + "6A39C994" + "8FE30BBF" + "F2660BE1" + "715A4589"
            + "334C74C7", 16);

    private static final BigInteger yg = new BigInteger("BC3736A2" + "F4F6779C"
            + "59BDCEE3" + "6B692153" + "D0A9877C" + "C62A4740" + "02DF32E5"
            + "2139F0A0", 16);

    public static final BigInteger n = new BigInteger("FFFFFFFE" + "FFFFFFFF"
            + "FFFFFFFF" + "FFFFFFFF" + "7203DF6B" + "21C6052B" + "53BBF409"
            + "39D54123", 16);

    public static final ECCurve curve;
    public static ECPoint G;
    public static final ECDomainParameters domain;


    static {
        curve = GMNamedCurves.getByName("sm2p256v1").getCurve();
        G = curve.createPoint(xg, yg);
        domain = new ECDomainParameters(SM2Util.curve, SM2Util.G, SM2Util.n);
    }

    private SM2Util(){}

}
