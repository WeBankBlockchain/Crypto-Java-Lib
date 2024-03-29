/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.webank.blockchain.hsm.crypto.sdf;

public final class AlgorithmType {
    public static final AlgorithmType SM2 = new AlgorithmType("SM2", SDFJNI.SM2_get());
    public static final AlgorithmType SM3 = new AlgorithmType("SM3", SDFJNI.SM3_get());
    public static final AlgorithmType SM4_CBC = new AlgorithmType("SM4_CBC", SDFJNI.SM4_CBC_get());

    public final int swigValue() {
        return swigValue;
    }

    public String toString() {
        return swigName;
    }

    public static AlgorithmType swigToEnum(int swigValue) {
        if (swigValue < swigValues.length
                && swigValue >= 0
                && swigValues[swigValue].swigValue == swigValue) return swigValues[swigValue];
        for (int i = 0; i < swigValues.length; i++)
            if (swigValues[i].swigValue == swigValue) return swigValues[i];
        throw new IllegalArgumentException(
                "No enum " + AlgorithmType.class + " with value " + swigValue);
    }

    private AlgorithmType(String swigName) {
        this.swigName = swigName;
        this.swigValue = swigNext++;
    }

    private AlgorithmType(String swigName, int swigValue) {
        this.swigName = swigName;
        this.swigValue = swigValue;
        swigNext = swigValue + 1;
    }

    private AlgorithmType(String swigName, AlgorithmType swigEnum) {
        this.swigName = swigName;
        this.swigValue = swigEnum.swigValue;
        swigNext = this.swigValue + 1;
    }

    private static AlgorithmType[] swigValues = {SM2, SM3, SM4_CBC};
    private static int swigNext = 0;
    private final int swigValue;
    private final String swigName;
}
