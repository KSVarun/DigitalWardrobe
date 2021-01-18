package com.hci.digitalwardrobe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BarCodeActivity extends AppCompatActivity {
    public static TextView resulttextview;
    Button scan_btn;
    Button toast_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);

        resulttextview = findViewById(R.id.barcodetextview);
        scan_btn = findViewById(R.id.buttonscan);
        toast_btn = findViewById(R.id.buttontoast);

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
            }
        });
        toast_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BarCodeActivity.this,resulttextview.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}