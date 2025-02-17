package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void openAddClothes(View view) {
        Intent intent = new Intent(getApplicationContext(), AddClothesActivity.class);
        startActivity(intent);
    }

    public void openWardrobe(View view) {
        Intent intent = new Intent(getApplicationContext(), MyWardrobeActivity.class);
        startActivity(intent);
    }

    public void openRecommendActivities(View view) {
        Intent intent = new Intent(getApplicationContext(), RecommendActivity.class);
        startActivity(intent);
    }

    public void openRecommendTravel(View view){
        Intent intent = new Intent(getApplicationContext(), TravelRecommendation.class);
        startActivity(intent);
    }
}