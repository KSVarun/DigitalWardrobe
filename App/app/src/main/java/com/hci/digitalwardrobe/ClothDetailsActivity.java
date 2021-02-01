package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hci.digitalwardrobe.calls.UploadClothesPrediction;
import com.hci.digitalwardrobe.models.PredictClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClothDetailsActivity extends AppCompatActivity {

    TextInputEditText dressName;
    AutoCompleteTextView dressCategory;
    TextInputEditText dressColour;
    AutoCompleteTextView dressWeather;
    TextInputEditText minWeather;
    TextInputEditText maxWeather;
    ImageView clothImage;

    PredictClothesModel predictedAttributes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_details);

        dressName = findViewById(R.id.dress_name);
        dressCategory = findViewById(R.id.dress_category_dropdown);
        dressColour = findViewById(R.id.dress_colour);
        dressWeather = findViewById(R.id.dress_weather_dropdown);
        minWeather = findViewById(R.id.weather_minimum);
        maxWeather = findViewById(R.id.weather_maximum);
        clothImage = findViewById(R.id.cloth_image);


        String[] category_options = new String[] {"Shirt", "Sweater", "T-shirt", "Outerwear", "Suit",
                "Tank Top", "Dress", "Trousers", "Rain Jacket", "Jacket", "Formal Wear"};

        String[] weather_options = new String[] {"Summer", "Spring", "Autumn", "Winter",
                "Rainy", "All"};

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>
                (this, R.layout.cloth_options, category_options);

        ArrayAdapter<String> weatherAdapter =  new ArrayAdapter<>
                (this, R.layout.weather_options, weather_options);

        dressCategory.setAdapter(categoryAdapter);
        dressWeather.setAdapter(weatherAdapter);

        Intent intent = getIntent();
        predictedAttributes = (PredictClothesModel)
                intent.getSerializableExtra("PredictedAttributes");

        Picasso.get().load(getApplication().getApplicationContext().getString
                (R.string.MEDIA_SERVER_URL) + predictedAttributes.getImage()).into(clothImage);

        dressCategory.setText(predictedAttributes.getCategory());

    }

    public void addClothes(View view) {
        UploadClothesPrediction api = WardrobeFactory.getInstance().getRetrofit().create(UploadClothesPrediction.class);
        prepareDataForAddClothesModel(predictedAttributes);

        Call<PredictClothesModel> call = api.addClothes(predictedAttributes);
        call.enqueue(new Callback<PredictClothesModel>() {
            @Override
            public void onResponse(Call<PredictClothesModel> call, Response<PredictClothesModel> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(),"Cloth added",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Try again",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AddClothesActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void onFailure(Call<PredictClothesModel> call, Throwable t) {
                Log.d("Failed========", t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private PredictClothesModel prepareDataForAddClothesModel(PredictClothesModel predictedAttributes) {
        dressName = findViewById(R.id.dress_name);
        dressCategory = findViewById(R.id.dress_category_dropdown);
        dressColour = findViewById(R.id.dress_colour);
        dressWeather = findViewById(R.id.dress_weather_dropdown);
        minWeather = findViewById(R.id.weather_minimum);
        maxWeather = findViewById(R.id.weather_maximum);

        predictedAttributes.setName(dressName.getText().toString());
        predictedAttributes.setCategory(dressCategory.getText().toString());
        predictedAttributes.setColour(dressColour.getText().toString());
        predictedAttributes.setWeather(dressWeather.getText().toString());
        predictedAttributes.setMin_temp(minWeather.getText().toString());
        predictedAttributes.setMax_temp(maxWeather.getText().toString());
        predictedAttributes.setUser(WardrobeFactory.getUsername());

        return predictedAttributes;
    }
}