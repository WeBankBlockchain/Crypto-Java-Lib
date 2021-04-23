package com.webank.wedpr.crypto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aaronchu
 * @Description
 * @data 2020/06/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoResult {
    public String signature;
    public String publicKey;
    public String privateKey;
    public String hash;
    public boolean booleanResult;
    public String encryptedData;
    public String decryptedData;
    public String vrfProof;
    public String wedprErrorMessage;
}
