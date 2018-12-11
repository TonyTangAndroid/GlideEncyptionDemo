#!/usr/bin/env bash
cp settings.gradle settings.gradle.bk
cp apk.sample settings.gradle
gradle clean
cd okhttp3
gradle installRelease
adb shell am start -n com.github.tonytanganadroid.glide.okhttp3.demo/.MainActivity
mv settings.gradle.bk settings.gradle