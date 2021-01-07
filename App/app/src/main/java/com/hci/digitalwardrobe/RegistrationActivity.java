package com.hci.digitalwardrobe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.already_member_btn), "transition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }
}