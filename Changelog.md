## v1.0.3
(2022-08-22)

**更新**

升级`java-crypto-core`依赖的动态库为[WeDPR-Lab-Crypto](https://github.com/WeBankBlockchain/WeDPR-Lab-Crypto)的`1.3.0`版本

**修复**
删除动态库中未实现的`ecies`相关接口

## v1.0.2
(2022-05-10)

**新增**
支持mac os m1

**修复**
修复linux arm动态库加载失败的问题

## v1.0.0
(2021-07-28)

**新增**
* java-crypto-core: 提供了SM2，SM3，SM4，secp256k，keccak256H，eddsa curve 25519 VRF等相关密码算法。
* hsm-crypto: 提供了使用硬件密码模块的SM2、SM3、SM4密码算法。