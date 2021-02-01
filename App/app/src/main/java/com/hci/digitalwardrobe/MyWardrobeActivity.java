package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.hci.digitalwardrobe.calls.GetAllClothes;
import com.hci.digitalwardrobe.calls.UserApi;
import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.PredictClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.validation.SchemaFactoryLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWardrobeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wardrobe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GetAllClothes api = WardrobeFactory.getInstance().getRetrofit().create(GetAllClothes.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("User", WardrobeFactory.getInstance().getUsername());
        Call<List<ClothesModel>> call = api.getAllClothes(map);

        call.enqueue(new Callback<List<ClothesModel>>() {
            @Override
            public void onResponse(Call<List<ClothesModel>> call, Response<List<ClothesModel>> response) {
                if (response.code() == 200) {
                    List<ClothesModel> dressList = response.body();
                    postProcess(dressList);
                }
            }

            @Override
            public void onFailure(Call<List<ClothesModel>> call, Throwable t) {

            }
        });
    }

    public void postProcess(List<ClothesModel> items) {

        ClothAdapter clothAdapter = new ClothAdapter(this, items);
        ListView listView =findViewById(R.id.recycler_view);
        listView.setAdapter(clothAdapter);
    }
}