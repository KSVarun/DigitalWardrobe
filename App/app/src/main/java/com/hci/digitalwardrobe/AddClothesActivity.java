package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hci.digitalwardrobe.calls.UploadClothesPrediction;
import com.hci.digitalwardrobe.calls.UserApi;
import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.PredictClothesModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClothesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WardrobeFactory.init(getApplicationContext());
    }

    public void openOptions(View view) {
        //startActivity(new Intent(getApplicationContext(), ImageSelectActivity.class));
        //ImageSelectActivity.startImageSelectionForResult(this, true, true, true, false, 1213);
        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CROP, false);//default is false
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
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            File file = new File(filePath);
            RequestBody fileBody;
            RequestBody textBody;
            fileBody = RequestBody.create(okhttp3.MediaType.parse("*/*"), file);
            textBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), "anuj");

            UploadClothesPrediction api = WardrobeFactory.getInstance().getRetrofit().create(UploadClothesPrediction.class);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), fileBody);

            Call<PredictClothesModel> call = api.predictClothes(body, textBody);
            call.enqueue(new Callback<PredictClothesModel>() {
                @Override
                public void onResponse(Call<PredictClothesModel> call, Response<PredictClothesModel> response) {
                    if (response.code() == 200) {
                        Log.i("2.0 getFeed > Full json res wrapped in gson => ",new Gson().toJson(response));

                        Intent intent = new Intent(getApplicationContext(), ClothDetailsActivity.class);
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