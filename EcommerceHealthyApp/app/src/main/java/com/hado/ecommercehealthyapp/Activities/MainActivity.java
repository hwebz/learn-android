package com.hado.ecommercehealthyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hado.ecommercehealthyapp.Adapter.BestDealAdapter;
import com.hado.ecommercehealthyapp.Adapter.CategoryAdapter;
import com.hado.ecommercehealthyapp.Domains.BestDeal;
import com.hado.ecommercehealthyapp.Domains.Category;
import com.hado.ecommercehealthyapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter categoryAdapter, bestDealAdapter;
    private RecyclerView categoryView, bestDealView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCategoryRecycler();
        initLocations();
        initBestDealRecycler();
    }

    private void initBestDealRecycler() {
        bestDealView = findViewById(R.id.bestDeals);
        bestDealView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        bestDealAdapter = new BestDealAdapter(getData());
        bestDealView.setAdapter(bestDealAdapter);
    }

    private void initLocations() {
        String[] locations = new String[] {
            "Los Angeles, USA",
            "New York, USA",
        };
        final Spinner locationSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            locations
        );
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
    }

    private void initCategoryRecycler() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("cat1", "Vegetables"));
        categories.add(new Category("cat2", "Fruits"));
        categories.add(new Category("cat3", "Dairy"));
        categories.add(new Category("cat4", "Drink"));
        categories.add(new Category("cat5", "Grain"));

        categoryView = findViewById(R.id.catView);
        categoryView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        categoryAdapter = new CategoryAdapter(categories);
        categoryView.setAdapter(categoryAdapter);
    }

    public ArrayList<BestDeal> getData() {
        ArrayList<BestDeal> bestDeals = new ArrayList<>();
        bestDeals.add(new BestDeal("Fresh Orange", "orange", 6.2, "Vibrant, citrus fruits with a sweet and tangy flavor. Known for their bright orange hue and juicy flesh, they are a rich source of vitamin C and provide a refreshing burst of energy.", 4.2));
        bestDeals.add(new BestDeal("Fresh Apple", "apple", 4.5, "Crisp and juicy fruits that come in a variety of colors, including red, green, and yellow. With a sweet or tart taste depending on the variety, apples are not only delicious but also packed with fiber, antioxidants, and essential nutrients.", 3.0));
        bestDeals.add(new BestDeal("Fresh Watermelon", "watermelon", 5.0, "Large, hydrating fruit characterized by its green rind and bright red or pink, juicy flesh. Sweet and refreshing, watermelon is perfect for quenching thirst on a hot day. It's also low in calories and high in vitamins A and C.", 2.5));
        bestDeals.add(new BestDeal("Fresh Pineapple", "pineaplle", 12.4, "tropical fruit with a unique combination of sweetness and tanginess. Characterized by its spiky, tough exterior and juicy, yellow flesh, pineapples are rich in vitamins, minerals, and enzymes. Packed with bromelain, an enzyme with potential health benefits, this refreshing fruit is enjoyed fresh, juiced, or as a delicious addition to both sweet and savory dishes.", 1.3));
        bestDeals.add(new BestDeal("Fresh Cherry", "berry", 8.1, "Small, round fruits that come in various colors, including red, yellow, and black. These sweet or tart berries are not only delicious but also offer antioxidants and anti-inflammatory compounds. Cherries are often enjoyed fresh, dried, or as part of desserts.", 3.4));
        bestDeals.add(new BestDeal("Fresh Strawberry", "strawberry", 7.0, "Luscious, heart-shaped berries with a sweet and slightly tart taste. Known for their vibrant red color and small seeds that dot their surface, strawberries are rich in vitamin C and antioxidants. They are versatile, enjoyed fresh, in salads, desserts, or as a topping", 5.0));

        return bestDeals;
    }
}