package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class ClothDetailsActivity extends AppCompatActivity {

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_details);

        textInputLayout = findViewById(R.id.dress_category);
        autoCompleteTextView = findViewById(R.id.dress_category_dropdown);

        String[] options = new String[] {"Shirt", "Sweater", "T-shirt", "Outerwear", "Suit",
                "Tank Top", "Dress", "Trousers", "Rain Jacket", "Jacket", "Formal Wear"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, R.layout.cloth_options, options);

        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}