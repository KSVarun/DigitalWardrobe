package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.hci.digitalwardrobe.models.WardrobeFactory;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();

        WardrobeFactory.init(getApplicationContext());

    }
}