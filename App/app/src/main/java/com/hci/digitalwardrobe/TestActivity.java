package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class TestActivity extends AppCompatActivity {

    Weather weather;
    TextView cityName;
    Button searchButton;
    TextView result;


    public void search(View view){
        cityName = findViewById(R.id.cityName);
        searchButton = findViewById(R.id.search);
        result = findViewById(R.id.result);

        String cName = cityName.getText().toString();


        String content;
        weather = new Weather();
        try {
            content =  weather.execute("https://api.openweathermap.org/data/2.5/weather?q="+ cName + "&appid=299e88b027bee5dcfe55b4566f1064b0").get();

            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString("weather");
            String mainTemperature = jsonObject.getString("main");
            JSONArray array = new JSONArray(weatherData);

            String main = "";
            String description = "";
            String temperature = "";

            for(int i = 0;i<array.length();i++){
                JSONObject weatherpart = array.getJSONObject(i);
                main = weatherpart.getString("main");

                description = weatherpart.getString("description");
            }

            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            float temperatureInt = Float.parseFloat(temperature);
            temperatureInt -= 273.15;
            temperature = Float.toString(temperatureInt);

            Log.i("main", main);
            Log.i("description", description);

            String resultText = "Main : "+ main +
                    "\nDescription : " + description +
                    "\nTemperature : " + temperature;

            result.setText(resultText);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }


}