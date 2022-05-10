# Crypto-Java-Lib

Crypto-Java-Lib收集了多个Java子密码库，包括
* java-crypto-core: 提供了SM2，SM3，SM4，secp256k，keccak256H，eddsa curve 25519 VRF等相关密码算法。
* hsm-crypto:  提供了使用硬件密码模块的SM2、SM3、SM4密码算法。

- **Rust源码**: https://github.com/WeBankBlockchain/WeDPR-Lab-Crypto

- 分支:
main

- commitID:
  4b7130a18ff7379728ff5926065f0d207e434106

- 编译:
  * libffi_java_sdk
    ```shell
    cd WeDPR-Lab-Crypto/ffi/ffi_java/ffi_java_crypto
    cargo build --features "wedpr_f_hex wedpr_f_signature_secp256k1 wedpr_f_hash_keccak256 wedpr_f_signature_sm2 wedpr_f_hash_sm3" --no-default-features --release
    ```

  * libffi_vrf_java_sdk
    ```shell
    cd WeDPR-Lab-Crypto/ffi/ffi_java/ffi_java_fisco_bcos_sdk
    cargo build --release
    ```
