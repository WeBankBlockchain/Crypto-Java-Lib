#!/bin/bash
bash gradlew --version
cd java-crypto-core
./gradlew build --info
cd ../hsm-crypto
./gradlew build -x test