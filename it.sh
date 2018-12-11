#!/usr/bin/env bash
source ./countFilesFunc.sh
rm -rf temp
cp settings.gradle settings.gradle.bk
cp aar.sample settings.gradle
gradle clean
gradle assembleRelease
cp okhttp3_lib/build/outputs/aar/okhttp3_lib-release.aar aars/glide.aar
unzip aars/glide.aar -d temp
unzip temp/classes.jar -d temp/classes
cp settings.gradle.bk settings.gradle
cd temp/classes/tony
badgeSDKNumOfFiles=$(count_files ./)
echo "dir:$(pwd)"
if [ "$badgeSDKNumOfFiles" -lt 3 ]; then
    echo "Bad result $badgeSDKNumOfFiles" >&2
    exit 1
fi
echo "success"
