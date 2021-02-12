package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.hci.digitalwardrobe.Classes.Clothes_temp;
import com.hci.digitalwardrobe.Classes.Weather;
import com.hci.digitalwardrobe.calls.UploadClothesAPI;
import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Activities activity = Activities.WORK;
    private TravelRecommendation.Condition condition = TravelRecommendation.Condition.CLEAR;

    public enum Activities {
        UNIVERSITY,SPORTS_OUTDOOR, WORK,
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
            String cloth = ":)";
            PredictCloth(activity,temperatureInt,condition);

            String resultText = "Main : "+ main +
                    "\nTemperature : " + temperature +
                    "\n Cloth : " + cloth;

            result.setText(resultText);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Function that calculates one clothing item based of activity, temperature and weather condition.
    public void PredictCloth(Activities activity, float temperature, TravelRecommendation.Condition condition){
        Map<String,String> map= new HashMap<>();
        map.put("Condition", condition.toString());
        map.put("Activity", activity.toString());
        map.put("Temperature", Float.toString(temperature));
        UploadClothesAPI api = WardrobeFactory.getInstance().getRetrofit().create(UploadClothesAPI.class);

        map.put("User", WardrobeFactory.getInstance().getUsername());


        Call<List<ClothesModel>> call = api.getActivityClothes(map);
        call.enqueue(new Callback<List<ClothesModel>>() {
            @Override
            public void onResponse(Call<List<ClothesModel>> call, Response<List<ClothesModel>> response) {
                if (response.code() == 200) {
                    Log.i("_____________________________________", "works");
                    List<ClothesModel> clothes = response.body();
                    int size = clothes.size();
                    String stringsize = Integer.toString(size);
                    ArrayList<Clothes_temp> clothlist = new ArrayList<>();
                    boolean trousers, Shirt,  Tshirt, Sweater, Jacket, Coat, Rainjacket, Suit, Dress, Tanktop, Sportshirt, Sportpants;
                    trousers = Shirt =  Tshirt = Sweater = Jacket = Coat = Rainjacket = Suit = Dress = Tanktop = Sportpants = Sportshirt = false;

                    for(ClothesModel c: clothes){
                        Log.d("Category:____________", c.getCategory());
                        String category = c.getCategory();
                        String sleevelength = c.getSleevelength();
                        if(category.equals("Trousers") && trousers == false) {
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            trousers = true;
                        }
                        else if(category.equals("Shirt")&& Shirt ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Shirt = true;
                        }
                        else if(category.equals("T-shirt")&& Tshirt ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Tshirt = true;
                        }
                        else if(category.equals("Sweater")&& Sweater ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Sweater = true;
                        }
                        else if(category.equals("Jacket")&& Jacket ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Jacket = true;
                        }
                        else if(category.equals("Coat")&& Coat ==false && Rainjacket == false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Coat = true;
                        }
                        else if(category.equals("Rain Jacket")&& Rainjacket ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Rainjacket = true;
                        }
                        else if(category.equals("Suit")&& Suit ==false && Dress == false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Suit = true;
                        }
                        else if(category.equals("Dress")&& Dress ==false && Suit ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Dress = true;
                        }
                        else if(category.equals("Tank Top")&& Tanktop ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Tanktop = true;
                        }
                        else if(category.equals("Sport Shirt")&& Sportshirt ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Sportshirt = true;
                        }
                        else if(category.equals("Sport Pants")&& Sportpants ==false){
                            clothlist.add(new Clothes_temp(sleevelength,category, c.getImage()));
                            Sportpants = true;
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("List", clothlist);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }
                else {
                    Toast.makeText(getApplicationContext(),"Authentication failed",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ClothesModel>> call, Throwable t) {
                Log.d("Failed========", t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
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
        String work = "Work";
        String sport = "Sports (Outdoor)";
        if(act.equals(sport)){
            activity = Activities.SPORTS_OUTDOOR;
        }
        else if(act.equals(university)){
            activity = Activities.UNIVERSITY;
        }
        else if(act.equals(work)){
            activity = Activities.WORK;
        }
        else activity = Activities.UNIVERSITY;
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