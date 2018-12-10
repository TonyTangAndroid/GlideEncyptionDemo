package com.github.tonytanganadroid.glideencyptiondemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.Excludes;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;
import java.io.InputStream;

@GlideModule
@Excludes({com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule.class})
public class UsherAppGlideModule extends AppGlideModule {

    public static final String FOLDER_NAME = "glide_encryption";
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 250;//250 Megabytes

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        builder.setDiskCache(new DiskLruCacheFactory(directory.getAbsolutePath(), FOLDER_NAME, DISK_CACHE_SIZE));

    }

    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.append(String.class, InputStream.class, new UsherImageModelLoader.Factory());
        EncryptionFacebookConcealRepository encryptionRepository = new EncryptionFacebookConcealRepository(context);
        registry.prepend(InputStream.class, new SecureEncoder(encryptionRepository));
        registry.prepend(File.class, Bitmap.class, new SecureDecoder(encryptionRepository, glide.getBitmapPool()));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}