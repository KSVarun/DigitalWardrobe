package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyWardrobeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<IndividualClothDetails> items = new ArrayList();
    ClothesAdapter clothesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wardrobe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items.add(new IndividualClothDetails("Print Something", "Casual"));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clothesAdapter = new ClothesAdapter(recyclerView, this, items);
        recyclerView.setAdapter(clothesAdapter);

        clothesAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=3) {
                    items.add(null);
                    clothesAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            clothesAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index + 10;
                            for(int i=index; i<end; i++){
                                String cloth_name = UUID.randomUUID().toString();
                                String cloth_category = UUID.randomUUID().toString();
                                IndividualClothDetails item = new IndividualClothDetails(cloth_name, cloth_category);
                                items.add(item);
                            }
                            clothesAdapter.notifyDataSetChanged();
                            clothesAdapter.setLoaded();
                        }
                    }, 4000);
                }
                else
                    Toast.makeText(MyWardrobeActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}