package com.hado.secondweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hado.secondweatherapp.Adapters.HourlyAdapter;
import com.hado.secondweatherapp.Domains.Hourly;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        TextView next7Days = (TextView) findViewById(R.id.next7Days);
        next7Days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next7DaysIntent = new Intent(MainActivity.this, FutureForecastsActivity.class);
                startActivity(next7DaysIntent);
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<Hourly> items = new ArrayList<>();

        items.add(new Hourly("9 pm", 28, "cloudy"));
        items.add(new Hourly("10 pm", 29, "sunny"));
        items.add(new Hourly("11 pm", 30, "wind"));
        items.add(new Hourly("12 pm", 31, "rain"));
        items.add(new Hourly("13 pm", 32, "storm"));

        recyclerView = findViewById(R.id.hourlyTemp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }
}