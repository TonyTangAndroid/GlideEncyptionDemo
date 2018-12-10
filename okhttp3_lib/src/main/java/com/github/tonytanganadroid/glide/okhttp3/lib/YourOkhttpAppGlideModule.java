package com.github.tonytanganadroid.glide.okhttp3.lib;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.Excludes;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

@GlideModule
@Excludes({com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule.class})
public class YourOkhttpAppGlideModule extends AppGlideModule {

    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client());
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
    }

    private OkHttpClient client() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}