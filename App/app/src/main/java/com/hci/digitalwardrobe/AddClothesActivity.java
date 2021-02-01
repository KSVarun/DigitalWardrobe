package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hci.digitalwardrobe.calls.UploadClothesPrediction;
import com.hci.digitalwardrobe.models.PredictClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import java.io.File;
import java.util.Timer;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClothesActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.loading_spinner);
    }

    public void openOptions(View view) {

        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
        intent.putExtra(ImageSelectActivity.FLAG_CROP, false);
        startActivityForResult(intent, 1213);
    }

    public void barcodeView(View view){
        Intent intent = new Intent(this, BarCodeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            File file = new File(filePath);
            RequestBody fileBody;
            fileBody = RequestBody.create(okhttp3.MediaType.parse("*/*"), file);

            UploadClothesPrediction api = WardrobeFactory.getInstance().getRetrofit().create(UploadClothesPrediction.class);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), fileBody);



            Call<PredictClothesModel> call = api.predictClothes(body);

            call.enqueue(new Callback<PredictClothesModel>() {

                @Override
                public void onResponse(Call<PredictClothesModel> call, Response<PredictClothesModel> response) {

                    if (response.code() == 200) {

                        PredictClothesModel data = response.body();

                        Intent intent = new Intent(getApplicationContext(), ClothDetailsActivity.class);
                        intent.putExtra("PredictedAttributes", data);
                        progressBar.setVisibility(View.GONE);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Try again",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<PredictClothesModel> call, Throwable t) {
                    Log.d("Failed========", t.getMessage());
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}