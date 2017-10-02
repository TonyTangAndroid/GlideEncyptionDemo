package com.github.tonytanganadroid.glideencyptiondemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hugo.weaving.DebugLog;

@DebugLog
public class SecureDecoder implements ResourceDecoder<File, Bitmap> {
    private final EncryptionRepository encryptionRepository;
    private final BitmapPool bitmapPool;

    public SecureDecoder(EncryptionRepository encryptionRepository, BitmapPool bitmapPool) {
        this.encryptionRepository = encryptionRepository;
        this.bitmapPool = bitmapPool;

    }

    private String getStringFromStream(InputStream inputStream) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null) {
            stringBuilder.append(receiveString);
        }

        inputStream.close();
        return stringBuilder.toString();
    }


    public boolean handles(File source, Options options) throws IOException {
        String base64EncryptedData = getStringFromStream(new FileInputStream(source));
        String decryptedData = encryptionRepository.decrypt(base64EncryptedData);
        return !decryptedData.equals(base64EncryptedData);
    }

    public Resource<Bitmap> decode(File source, int width, int height, Options options) throws IOException {
        String base64EncryptedData = getStringFromStream(new FileInputStream(source));
        String decryptedData = encryptionRepository.decrypt(base64EncryptedData);
        byte[] encodeByte = Base64.decode(decryptedData, Base64.NO_WRAP);
        return BitmapResource.obtain(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length), bitmapPool);
    }

}
