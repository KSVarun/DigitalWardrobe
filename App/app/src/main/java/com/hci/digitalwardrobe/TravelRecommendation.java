package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TravelRecommendation extends AppCompatActivity {

    // UI ELEMENTS
    Weather weather;
    TextView cityName;
    Button searchButton;
    TextView result;

    // VARIABLES FOR WEATHER DATA
    private double lat, lon;
    private double mintemp,maxtemp;

    // TODO implement function that gets user gender
    private String gender = "male";

    private final float POP_THRESHHOLD = 0.3f;

    public enum Condition{
        RAIN,SNOW,CLEAR,CLOUDS
    }


    // Function to retrieve the longitude and latitude for a specific city name
    public void find_coord(String city){
        if(Geocoder.isPresent()){
            try {
                Geocoder gc = new Geocoder(this);
                List<Address> addresses= gc.getFromLocationName(city, 5); // get the found Address Objects

                for(Address a : addresses){
                    if(a.hasLatitude() && a.hasLongitude()){
                        lat = a.getLatitude();
                        lon = a.getLongitude();

                    }
                }
            } catch (IOException e) {
                // handle the exception
            }
        }

    }

    // Function to call Weather api and get prediction of the weather of the specific Location for the current and the next 7 days.
    public void search(View view){
        cityName = findViewById(R.id.cityName2);
        searchButton = findViewById(R.id.search2);
        result = findViewById(R.id.result2);

        String cName = cityName.getText().toString();
        find_coord(cName);
        String lats = Double.toString(lat);
        String lons = Double.toString(lon);

        String content;
        weather = new Weather();
        try {
            content =  weather.execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lats + "&lon=" + lons +"&units=metric&exclude=current,minutely,hourly&appid=299e88b027bee5dcfe55b4566f1064b0").get();
            // Retrieve data from the API as JSON-files and store them in corrosponding variables
            //JSON
            JSONObject jsonObject = new JSONObject(content);
            String weatherData = jsonObject.getString("daily");
            JSONArray array = new JSONArray(weatherData);

            String main = "";
            String description = "";
            String smintemp,smaxtemp = "";
            float temperature = 0;
            float pop = 0;

            for(int i = 0;i<array.length();i++){
                JSONObject weatherpart = array.getJSONObject(i);
                String temp = weatherpart.getString("temp");
                JSONObject jtemp = new JSONObject(temp);
                String stemperature = jtemp.getString("day");
                String spop = weatherpart.getString("pop");
                smintemp = jtemp.getString("min");
                smaxtemp = jtemp.getString("max");
                mintemp = Float.parseFloat(smintemp);
                maxtemp = Float.parseFloat(smaxtemp);
                temperature += Float.parseFloat(stemperature);
                pop += Float.parseFloat(spop);
                String weatherarray  = weatherpart.getString("weather");
                JSONArray jweatherarray = new JSONArray(weatherarray);

                for(int j = 0;j<jweatherarray.length();j++){
                    JSONObject weatherarraypart = jweatherarray.getJSONObject(j);
                    main += "\n" + "Day " + i + " " + weatherarraypart.getString("main");
                }

            }
            /*
            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            float temperatureInt = Float.parseFloat(temperature);
            temperatureInt -= 273.15;
             */
            temperature = temperature / array.length();
            pop = pop / array.length();
            String sstemperature = Float.toString(temperature);
            String spop = Float.toString(pop);

            String cloth = predictCloth(temperature,pop);

            String resultText = "Main : "+ main +
                    "\npop : " + spop +
                    "\nTemperature : " + sstemperature +
                    "\nWe recommend  : " + cloth;

            result.setText(resultText);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String predictCloth(float temp, float pop){
        String clothingitem = "";
        if(temp<0){
            if(pop<POP_THRESHHOLD) clothingitem = "Winter coat";
            else clothingitem = "Winter jacket";
        }
        else if(0<=temp && temp<10){
            if(pop<POP_THRESHHOLD) clothingitem = "down jacket";
            else clothingitem = "Rain coat";
        }
        else if(10<=temp && temp<18){
            if(pop<POP_THRESHHOLD) clothingitem = "Jacket";
            else clothingitem = "Rain jacket";
        }
        else if(18<=temp && temp<22){
            if(pop<POP_THRESHHOLD) clothingitem = "Shirt";
            else clothingitem = "Windbreaker";
        }
        else{
            if(pop<POP_THRESHHOLD) clothingitem = "T-Shirt";
            else clothingitem = "Windbreaker";
        }
        return clothingitem;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_recommendation);
    }
}