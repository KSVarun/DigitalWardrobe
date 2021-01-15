package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RecommendActivity extends AppCompatActivity {

    //API variables
    Weather weather;

    //UI ELEMENTS
    TextView cityName;
    Button searchButton;
    TextView result;
    Spinner mySpinner;
    TravelRecommendation.Condition con;


    // TODO implement function that gets user gender
    String gender = "female";
    private Activitys activity = Activitys.BUSINESS;
    private TravelRecommendation.Condition condition = TravelRecommendation.Condition.CLEAR;

    public enum Activitys{
        UNIVERSITY,SPORTS_OUTDOOR,BUSINESS,
    }
    //Function that gets called when searchButton gets clicked. Calculates Weather data and calls other functions to predict one clothing item.
    public void search(View view){

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
            String temperature = "";
            String id = "";

            for(int i = 0;i<array.length();i++){
                JSONObject weatherpart = array.getJSONObject(i);
                main = weatherpart.getString("main");
                id = weatherpart.getString("id");

            }

            JSONObject mainPart = new JSONObject(mainTemperature);
            temperature = mainPart.getString("temp");
            float temperatureInt = Float.parseFloat(temperature);
            temperatureInt -= 273.15;
            temperature = Float.toString(temperatureInt);
            Log.i("ID", id);
            int idInt = Integer.parseInt(id);

            setCondition(idInt);
            String spinner_text = mySpinner.getSelectedItem().toString();
            setActivity(spinner_text);
            Log.i("condition", condition.toString());

            String cloth = PredictCloth(activity,temperatureInt,condition);

            String resultText = "Main : "+ main +
                    "\nTemperature : " + temperature +
                    "\n Cloth : " + cloth;

            result.setText(resultText);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Function that calculates one clothing item based of activity, temperature and weather condition.
    public String PredictCloth(Activitys activity, float temperature, TravelRecommendation.Condition condition){
        String clothitem = "";
        switch (activity){
            case SPORTS_OUTDOOR:
                switch(condition) {
                    case RAIN:
                    case SNOW:
                        if (temperature < 0) clothitem = "Winter jacket";
                        else if (temperature > 0 && temperature < 10) clothitem = "Rain jacket";
                        else if (temperature > 10 && temperature < 18) clothitem = "Rain jacket";
                        else if (temperature > 18 && temperature < 22) clothitem = "Sport shirt";
                        else clothitem = "Sport shirt";
                        break;
                    case CLEAR:
                    case CLOUDS:
                        if (temperature < 0) clothitem = "Winter jacket";
                        else if (temperature > 0 && temperature < 10) clothitem = "Jacket";
                        else if (temperature > 10 && temperature < 18) clothitem = "Long sleeve sport shirt";
                        else if (temperature > 18 && temperature < 22) clothitem = "Sport shirt";
                        else clothitem = "Sport shirt";
                        break;
                    default:
                        clothitem = "t-shirt";
                }
            break;
            case BUSINESS:

                if(gender=="male"){
                    if(temperature<22) clothitem = "Suit";
                    else clothitem = "Shirt";
                }
                if(gender=="female"){
                    if(temperature<22) clothitem = "Blazer";
                    else clothitem = "Shirt";
                }
            break;
            case UNIVERSITY:
                if(gender=="male"){
                    if (temperature < 0) clothitem = "Sweatshirt";
                    else if (temperature > 0 && temperature < 10) clothitem = "Sweatshirt";
                    else if (temperature > 10 && temperature < 18) clothitem = "Long sleeve shirt";
                    else if (temperature > 18 && temperature < 22) clothitem = "shirt";
                    else clothitem = "shirt";
                }
                if(gender=="female"){
                    if (temperature < 0) clothitem = "Sweatshirt";
                    else if (temperature > 0 && temperature < 10) clothitem = "Sweatshirt";
                    else if (temperature > 10 && temperature < 18) clothitem = "Blouse";
                    else if (temperature > 18 && temperature < 22) clothitem = "Top";
                    else clothitem = "Top";
                }
            break;
            default:
                clothitem = "t-shirt";
        }

        return clothitem;
    }

    // Function to set the Weather condition based on API data.
    public void setCondition(int id){
        if(id>199&&id<600)condition = TravelRecommendation.Condition.RAIN;
        else if(id>599&&id<700)condition = TravelRecommendation.Condition.SNOW;
        else if(id==800)condition = TravelRecommendation.Condition.CLEAR;
        else if(id>800)condition = TravelRecommendation.Condition.CLOUDS;
        else condition = TravelRecommendation.Condition.CLEAR;
    }


    // Function to set the activity based on what the user selected in the drop down menu mySpinner.
    public void setActivity(String act){
        String university = "University";
        String business = "Business";
        String sport = "Sports (Outdoor)";
        if(act.equals(sport)){
            activity = Activitys.SPORTS_OUTDOOR;
        }
        else if(act.equals(university)){
            activity = Activitys.UNIVERSITY;
        }
        else if(act.equals(business)){
            activity = Activitys.BUSINESS;
        }
        else activity = Activitys.UNIVERSITY;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        cityName = findViewById(R.id.cityName);
        searchButton = findViewById(R.id.search);
        result = findViewById(R.id.result);
        mySpinner = (Spinner) findViewById(R.id.spinnerID);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RecommendActivity.this,
                R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mySpinner.setAdapter(myAdapter);
    }


}