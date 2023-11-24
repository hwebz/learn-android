package com.hado.ecommercehealthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hado.ecommercehealthyapp.R;

public class IntroActivity extends AppCompatActivity {
    private Button introBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        introBtn = findViewById(R.id.introBtn);
        introBtn.setOnClickListener(view -> {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        });
    }
}