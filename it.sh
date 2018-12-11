#!/usr/bin/env bash
cp settings.gradle settings.gradle.bk
cp aar.sample settings.gradle
gradle clean
gradle assembleRelease
cp okhttp3_lib/build/outputs/aar/okhttp3_lib-release.aar aars/glide.aar
unzip aars/glide.aar -d temp
unzip temp/classes.jar -d temp/classes
cp settings.gradle.bk settings.gradle