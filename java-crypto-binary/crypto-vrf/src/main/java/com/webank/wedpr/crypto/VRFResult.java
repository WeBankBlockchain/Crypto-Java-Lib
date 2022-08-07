package com.webank.wedpr.crypto;

public class VRFResult {
    public byte[] vrfProof;
    public boolean booleanResult;
    public byte[] publicKey;
    public byte[] hash;

    public String wedprErrorMessage;
    /** Checks whether any error occurred. */
    public boolean hasError() {
        return wedprErrorMessage != null;
    }
}