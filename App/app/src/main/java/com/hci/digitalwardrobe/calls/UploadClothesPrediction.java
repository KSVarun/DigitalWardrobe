package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.PredictClothesModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadClothesPrediction {

    @Multipart
    @POST("predict/")
    Call<PredictClothesModel> predictClothes(@Part MultipartBody.Part file, @Part("file") RequestBody name);


}
