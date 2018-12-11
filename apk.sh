#!/usr/bin/env bash
adb shell pm uninstall lord.stark
cp settings.gradle settings.gradle.bk
cp apk.sample settings.gradle
gradle clean
cd okhttp3
gradle installRelease
echo "dir:$(pwd)"
adb shell monkey -p lord.stark -c android.intent.category.LAUNCHER 1
cd ..
mv settings.gradle.bk settings.gradle