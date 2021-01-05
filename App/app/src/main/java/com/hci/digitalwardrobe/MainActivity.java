package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureWeatherButton();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void configureWeatherButton() {
        Button nextButton = (Button) findViewById(R.id.ButtonWeather);
        Button Predictionbutton = (Button) findViewById(R.id.buttonWeatherPred);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecommendActivity.class));
            }
        });
        Predictionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TravelRecommendation.class));
            }
        });

    }
}