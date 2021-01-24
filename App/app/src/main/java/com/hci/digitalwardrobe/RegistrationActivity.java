package com.hci.digitalwardrobe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.stream.JsonReader;
import com.hci.digitalwardrobe.calls.UploadClothesAPI;
import com.hci.digitalwardrobe.calls.UserApi;
import com.hci.digitalwardrobe.models.ClothesModel;
import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void openLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.already_member_btn), "transition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegistrationActivity.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }

    public void userRegistrationSubmitButton(View view) {

            UserApi api = WardrobeFactory.getInstance().getRetrofit().create(UserApi.class);
            CreateUserModel createUserModel = prepareDataForUserModel();

            Call<CreateUserModel> call = api.createUser(createUserModel);
            call.enqueue(new Callback<CreateUserModel>() {
                @Override
                public void onResponse(Response<CreateUserModel> response) {
                    if (response.code() == 200) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        //Todo: Akshay redirect after success user registration
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("Failed========", t.getMessage());
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
    }

    private CreateUserModel prepareDataForUserModel() {
        EditText firstName = findViewById(R.id.register_input_first_name);
        EditText lastName = findViewById(R.id.register_input_last_name);
        RadioGroup genderRadioGroup = findViewById(R.id.register_gender);
        EditText userName = findViewById(R.id.register_username);
        EditText password = findViewById(R.id.register_password);
        EditText confirmPassword = findViewById(R.id.register_confirm_password);

        int index = genderRadioGroup.indexOfChild(findViewById(genderRadioGroup.getCheckedRadioButtonId()));
        RadioButton r = (RadioButton) genderRadioGroup.getChildAt(index);
        String selectedText = r.getText().toString();

        CreateUserModel createUserModel = new CreateUserModel();
        createUserModel.setFirstName(firstName.getText().toString());
        createUserModel.setLastName(lastName.getText().toString());
        createUserModel.setGender(selectedText);
        createUserModel.setUserName(userName.getText().toString());
        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_LONG).show();
            //Todo: throw an exception
        }
        createUserModel.setPassword(password.getText().toString());

        return createUserModel;
    }
}