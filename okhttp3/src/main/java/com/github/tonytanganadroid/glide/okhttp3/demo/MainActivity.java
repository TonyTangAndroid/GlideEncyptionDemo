package com.github.tonytanganadroid.glide.okhttp3.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    public static final String TEST_IMAGE_URL = "http://ec4.images-amazon.com/images/I/41oN%2BvzHTTL._SY300_.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ivDareDevil = findViewById(R.id.iv_daredevil);
        Glide.with(this).load(TEST_IMAGE_URL).into(ivDareDevil);
    }
}
