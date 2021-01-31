package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hci.digitalwardrobe.Classes.Clothes_temp;
import com.hci.digitalwardrobe.Classes.CustomAdapter;
import com.hci.digitalwardrobe.R;

import java.util.ArrayList;
import java.util.List;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        List<Clothes_temp> clothes = new ArrayList<>();
        /*
        clothes.add(new Clothes_temp("Das ist ein, ", "Test", R.drawable.background));
        clothes.add(new Clothes_temp("Das ist noch ein, ", "Test", R.drawable.background));
        clothes.add(new Clothes_temp("Das ist schon wieder ein, ", "Test", R.drawable.background));
        */
        Bundle bundle = getIntent().getExtras();
        clothes = bundle.getParcelableArrayList("List");
        ListAdapter Adapter = new CustomAdapter(this, clothes);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(Adapter);


    }
}