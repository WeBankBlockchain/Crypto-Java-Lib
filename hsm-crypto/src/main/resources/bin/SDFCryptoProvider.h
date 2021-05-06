/*
    This file is part of FISCO-BCOS.

    FISCO-BCOS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FISCO-BCOS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FISCO-BCOS.  If not, see <http://www.gnu.org/licenses/>.
*/
/** @file SDFCryptoProvider.h
 * @author maggiewu
 * @date 2021-02-01
 */
#pragma once
#include <stdio.h>
#include <cstdlib>
#include <list>
#include <string>
#include <cstring>
#include <vector>
using namespace std;
namespace hsm
{
enum AlgorithmType : uint32_t
{
    SM2 = 0x00020100,      // SGD_SM2_1
    SM3 = 0x00000001,      // SGD_SM3
    SM4_CBC = 0x00002002,  // SGD_SM4_CBC
};
namespace sdf
{
struct SDFCryptoResult{
    char * signature;
    char * publicKey;
    char * privateKey;
    bool result;
    char * hash;
    char * sdfErrorMessage;
};

SDFCryptoResult KeyGen(AlgorithmType algorithm);
SDFCryptoResult Sign(char * privateKey, AlgorithmType algorithm, char const* digest);
SDFCryptoResult SignWithInternalKey(unsigned int keyIndex, char * password, AlgorithmType algorithm, char const* digest);
SDFCryptoResult Verify(char * publicKey, AlgorithmType algorithm, char const* digest, char const* signature);
SDFCryptoResult VerifyWithInternalKey(unsigned int keyIndex, AlgorithmType algorithm, char const* digest,char const* signature);
SDFCryptoResult Hash(char * key, AlgorithmType algorithm, char const* message);
SDFCryptoResult ExportInternalPublicKey(unsigned int keyIndex, AlgorithmType algorithm);
SDFCryptoResult HashWithZ(char* key, AlgorithmType algorithm, char const* message);
}  // namespace crypto
}  // namespace dev
