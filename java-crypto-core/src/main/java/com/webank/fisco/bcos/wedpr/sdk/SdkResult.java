package com.webank.fisco.bcos.wedpr.sdk;

public class SdkResult {
    public String publicKey;
    public String privateKey;
    public String hash;
    public boolean booleanResult;
    public String vrfProof;
    public String wedprErrorMessage;
    /** Checks whether any error occurred. */
    public boolean hasError() {
        return wedprErrorMessage != null;
    }
}
