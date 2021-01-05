package com.hci.digitalwardrobe.calls;

import com.hci.digitalwardrobe.models.ClothesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UploadClothesAPI {

    @GET("get")
    Call<ClothesModel> getClothes();
}
