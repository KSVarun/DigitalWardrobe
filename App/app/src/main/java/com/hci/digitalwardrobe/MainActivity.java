package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.zxing.BarcodeFormat;
import com.hci.digitalwardrobe.calls.UploadClothesAPI;
import com.hci.digitalwardrobe.models.ClothesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
public class MainActivity extends AppCompatActivity {


    private static final String API_BASE_URL = "localhost:8000";
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setContentView(R.layout.activity_main);

        configureWeatherButton();

        apiTestButton ();

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
        Button barcode_btn = (Button) findViewById(R.id.BarCodeScanner);
        barcode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BarCodeActivity.class));
            }
        });

    }

    private void apiTestButton() {
        Button nextButton = (Button) findViewById(R.id.ButtonAPI);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadClothesAPI api = retrofit.create(UploadClothesAPI.class);
                Call<ClothesModel> call = api.getClothes();
            }
        });

    }
}