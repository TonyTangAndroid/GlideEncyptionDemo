package com.github.tonytanganadroid.glideencyptiondemo;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import hugo.weaving.DebugLog;

@DebugLog
public class SecureEncoder implements Encoder<InputStream> {
    private final EncryptionRepository encryptionRepository;

    public SecureEncoder(EncryptionRepository encryptionRepository) {
        this.encryptionRepository = encryptionRepository;
    }

    public boolean encode(@NonNull InputStream data, @NonNull File file, @NonNull Options options) {
        try {

            String anyText = base64Data(data);
            String base64EncryptedData = encryptionRepository.encrypt(anyText);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(base64EncryptedData);
            outputStreamWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String base64Data(InputStream inputStream) throws IOException {
        byte[] inputStreamToByteArray = inputStreamToByteArray(inputStream);
        return Base64.encodeToString(inputStreamToByteArray, Base64.NO_WRAP);
    }

    private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int readCount;
        byte[] data = new byte[16384];
        while ((readCount = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, readCount);
        }
        buffer.flush();

        return buffer.toByteArray();
    }


}
