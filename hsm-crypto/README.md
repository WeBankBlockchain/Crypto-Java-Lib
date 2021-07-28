# Crypto-Java-Lib/hsm-crypto

This is java library of hardware secure module. Help your java project utilize PCI encryption card which satisfied the《密码设备通用接口规范》.

If you have a PCI crypto device installed on your computer. 
```
gradlew build
```
Otherwise, please avoid run test, by using the following command. 
Or all the tests would failed.
```
gradlew build -x test
```