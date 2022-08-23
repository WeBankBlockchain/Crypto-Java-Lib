# Crypto-Java-Lib

Crypto-Java-Lib收集了多个Java子密码库，包括
* java-crypto-core: 提供了SM2，SM3，SM4，secp256k，keccak256H，eddsa curve 25519 VRF等相关密码算法。
* hsm-crypto:  提供了使用硬件密码模块的SM2、SM3、SM4密码算法。

- **Rust源码**: https://github.com/WeBankBlockchain/WeDPR-Lab-Crypto

- 分支:
main

- 编译:
  ```bash
  $ cargo clean && cd ffi/ffi_java/ffi_java_crypto && cargo build --release --no-default-features --features "wedpr_f_hex wedpr_f_signature_secp256k1 wedpr_f_hash_keccak256 wedpr_f_signature_sm2 wedpr_f_hash_sm3 wedpr_f_vrf_curve25519"
  # rename library name
  # macos
  $ mv target/release/libffi_java_crypto.dylib libffi_java_sdk.dylib
  # linux
  $ mv target/release/libffi_java_crypto.so libffi_java_sdk.so
  # arm linux
  $ mv target/release/libffi_java_sdk_arm.so libffi_java_sdk_arm.so
  # macos m1
  $ mv target/release/libffi_java_sdk_arm.dylib libffi_java_sdk_arm.dylib
  # windows
  mv target\release\ffi_java_crypto.dll ffi_java_sdk.dll
  
  # copy to resource path
  $ cp libffi_java_sdk* java-crypto-core/src/main/resources/WeDPR_dynamic_lib/
  $ cp ffi_java_sdk.dll java-crypto-core/src/main/resources/WeDPR_dynamic_lib/
  ```

- 动态库下载链接: https://github.com/WeBankBlockchain/WeDPR-Lab-Crypto/releases