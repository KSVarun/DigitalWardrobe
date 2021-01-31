package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.hci.digitalwardrobe.calls.UploadClothesAPI;
import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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