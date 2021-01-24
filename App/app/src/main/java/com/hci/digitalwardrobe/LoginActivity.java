package com.hci.digitalwardrobe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hci.digitalwardrobe.calls.UserApi;
import com.hci.digitalwardrobe.models.CreateUserModel;
import com.hci.digitalwardrobe.models.WardrobeFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void redirectToRegistrationScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.create_account_btn), "transition");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
        startActivity(intent, options.toBundle());
        finish();
    }

    public void loginButton(View view) {

            UserApi api = WardrobeFactory.getInstance().getRetrofit().create(UserApi.class);
            CreateUserModel createUserModel = prepareDataForUserModel();

            Call<CreateUserModel> call = api.authenticateUser(createUserModel);
            call.enqueue(new Callback<CreateUserModel>() {
                @Override
                public void onResponse(Response<CreateUserModel> response) {
                    if (response.code() == 200) {
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Authentication failed",
                                Toast.LENGTH_LONG).show();
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
        EditText userName = findViewById(R.id.login_username);
        EditText password = findViewById(R.id.login_password);

        CreateUserModel createUserModel = new CreateUserModel();
        createUserModel.setUserName(userName.getText().toString());
        createUserModel.setPassword(password.getText().toString());

        return createUserModel;
    }

}