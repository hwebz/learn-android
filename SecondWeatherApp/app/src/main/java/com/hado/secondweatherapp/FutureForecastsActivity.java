package com.hado.secondweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hado.secondweatherapp.Adapters.FutureAdapter;
import com.hado.secondweatherapp.Domains.FutureDomain;

import java.util.ArrayList;

public class FutureForecastsActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterFuture;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.future_forecast);

        initRecyclerView();

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<FutureDomain> items = new ArrayList<>();

        items.add(new FutureDomain("Sat", "storm", "Storm", 21, 7));
        items.add(new FutureDomain("Sun", "cloudy", "Storm", 23, 8));
        items.add(new FutureDomain("Mon", "windy", "Storm", 24, 9));
        items.add(new FutureDomain("Tue", "snowy", "Storm", 22, 7));
        items.add(new FutureDomain("Wed", "sunny", "Storm", 27, 6));
        items.add(new FutureDomain("Thu", "rainy", "Storm", 20, 9));
        items.add(new FutureDomain("Fri", "wind", "Wind", 25, 12));
        items.add(new FutureDomain("Sat", "storm", "Storm", 21, 7));
        items.add(new FutureDomain("Sun", "cloudy", "Storm", 23, 8));
        items.add(new FutureDomain("Mon", "windy", "Storm", 24, 9));
        items.add(new FutureDomain("Tue", "snowy", "Storm", 22, 7));
        items.add(new FutureDomain("Wed", "sunny", "Storm", 27, 6));
        items.add(new FutureDomain("Thu", "rainy", "Storm", 20, 9));
        items.add(new FutureDomain("Fri", "wind", "Wind", 25, 12));

        recyclerView = findViewById(R.id.futureTemp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterFuture = new FutureAdapter(items);
        recyclerView.setAdapter(adapterFuture);
    }
}
