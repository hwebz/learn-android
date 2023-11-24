package com.hado.ecommercehealthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hado.ecommercehealthyapp.Adapter.SimilarProductsAdapter;
import com.hado.ecommercehealthyapp.Domains.BestDeal;
import com.hado.ecommercehealthyapp.R;

public class DetailActivity extends AppCompatActivity {
    private BestDeal selectedProduct;
    private ImageView backBtn, itemImg;
    private TextView priceKgTxt, titleTxt, descriptionTxt, ratingTxt;
    private RatingBar ratingBar;
    private TextView weightTxt, plusBtn, minusBtn, totalTxt;
    private int weight = 1;
    private RecyclerView.Adapter similarAdapter;
    private RecyclerView similarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        getBundles();
        setVariables();
        initSimilarProducts();
    }

    private void initSimilarProducts() {
        similarView = findViewById(R.id.similarProductView);
        similarView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        similarAdapter = new SimilarProductsAdapter(new MainActivity().getData());
        similarView.setAdapter(similarAdapter);
    }

    private void setVariables() {
        backBtn.setOnClickListener(view -> {
            finish();
        });

        int drawableResourceId = getResources().getIdentifier(selectedProduct.getImgPath(), "drawable", this.getPackageName());

        Glide.with(DetailActivity.this)
                .load(drawableResourceId)
                .into(itemImg);

        priceKgTxt.setText(selectedProduct.getPrice() + "$/kg");
        titleTxt.setText(selectedProduct.getTitle());
        descriptionTxt.setText(selectedProduct.getDescription());
        ratingTxt.setText("(" + selectedProduct.getRate() + ")");
        ratingBar.setRating((float) selectedProduct.getRate());
        totalTxt.setText(weight * selectedProduct.getPrice() + "$");
        weightTxt.setText(weight + " kg");

        plusBtn.setOnClickListener(view -> {
            weight = weight + 1;
            weightTxt.setText(weight + " kg");
            totalTxt.setText(weight * selectedProduct.getPrice() + "$");
        });

        minusBtn.setOnClickListener(view -> {
            if (weight > 1) {
                weight = weight - 1;
                weightTxt.setText(weight + " kg");
                totalTxt.setText(weight * selectedProduct.getPrice() + "$");
            }
        });
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        itemImg = findViewById(R.id.img);
        priceKgTxt = findViewById(R.id.priceTag);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        ratingBar = findViewById(R.id.ratingBar);
        ratingTxt = findViewById(R.id.ratingTxt);
        weightTxt = findViewById(R.id.weightTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        totalTxt = findViewById(R.id.totalTxt);
    }

    private void getBundles() {
        selectedProduct = (BestDeal) getIntent().getSerializableExtra("selectedProduct");
    }
}