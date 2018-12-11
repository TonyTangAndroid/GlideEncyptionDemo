#!/usr/bin/env bash
cp settings.gradle settings.gradle.bk
cp apk.sample settings.gradle
gradle clean
cd okhttp3
gradle installDebug
adb shell am start -n lord.stark/stark.MainActivity
cd ..
mv settings.gradle.bk settings.gradle