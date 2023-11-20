package com.hado.moviesapp.Activities;

import static com.hado.moviesapp.Utils.DataUtils.readStreamText;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hado.moviesapp.Adapters.CategoryListAdapter;
import com.hado.moviesapp.Adapters.FilmListAdapter;
import com.hado.moviesapp.Adapters.SliderAdapter;
import com.hado.moviesapp.Domains.Endpoints;
import com.hado.moviesapp.Domains.Films;
import com.hado.moviesapp.Domains.Genre;
import com.hado.moviesapp.Domains.Genres;
import com.hado.moviesapp.Domains.SliderItem;
import com.hado.moviesapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Handler sliderHandler = new Handler();

    private RecyclerView.Adapter bestMoviesAdapter, categoryAdapter, upcomingMoviesAdapter;
    private RecyclerView bestMoviesRecycleView, categoryRecycleView, upcomingMoviesRecycleView;
    private RequestQueue bRequestQueue, uRequestQueue, cRequestQueue;
    private StringRequest bStringRequest, cStringRequest, uStringRequest;
    private ProgressBar bLoading, cLoading, uLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        banners();
        sendRequestBestMovies();
        sendRequestCategories();
        sendRequestUpcomingMovies();
    }

    private void sendRequestBestMovies() {
//        bRequestQueue = Volley.newRequestQueue(this);
        bLoading.setVisibility(View.VISIBLE);
//        bStringRequest = new StringRequest(Request.Method.GET, Endpoints.BEST_MOVIES, response -> {
            Gson gson = new Gson();
            bLoading.setVisibility(View.GONE);
            // From res/raw/best_movies.json
            Films films = gson.fromJson(readStreamText(getResources().openRawResource(R.raw.best_movies)), Films.class);
            // From API
            // Films films = gson.fromJson(response, Films.class);
            bestMoviesAdapter = new FilmListAdapter(films);
            bestMoviesRecycleView.setAdapter(bestMoviesAdapter);
//        }, error -> {
//            bLoading.setVisibility(View.GONE);
//            Log.i("MoviesApp", "sendRequestBestMovies - onErrorResponse: " + error.toString());
//        });
//        bRequestQueue.add(bStringRequest);
    }

    private void sendRequestCategories() {
//        cRequestQueue = Volley.newRequestQueue(this);
        cLoading.setVisibility(View.VISIBLE);
//        cStringRequest = new StringRequest(Request.Method.GET, Endpoints.CATEGORIES, response -> {
            Gson gson = new Gson();
            cLoading.setVisibility(View.GONE);
            // From res/raw/best_movies.json
            List<Genre> genresList = gson.fromJson(
                    readStreamText(getResources().openRawResource(R.raw.categories)),
                    new TypeToken<ArrayList<Genre>>(){}.getType()
            );
            Genres genres = new Genres(genresList);
            // From API
            // Genres genres = new Genres(gson.fromJson(response, new TypeToken<ArrayList<Genre>>(){}.getType()));
            categoryAdapter = new CategoryListAdapter(genres);
            categoryRecycleView.setAdapter(categoryAdapter);
//        }, error -> {
//            cLoading.setVisibility(View.GONE);
//            Log.i("MoviesApp", "sendRequestCategories - onErrorResponse: " + error.toString());
//        });
//        cRequestQueue.add(cStringRequest);
    }

    private void sendRequestUpcomingMovies() {
//        uRequestQueue = Volley.newRequestQueue(this);
        uLoading.setVisibility(View.VISIBLE);
//        uStringRequest = new StringRequest(Request.Method.GET, Endpoints.UPCOMING_MOVIES, response -> {
            Gson gson = new Gson();
            uLoading.setVisibility(View.GONE);
            // From res/raw/best_movies.json
            Films films = gson.fromJson(readStreamText(getResources().openRawResource(R.raw.upcoming_movies)), Films.class);
            // From API
            // Films films = gson.fromJson(response, Films.class);
            upcomingMoviesAdapter = new FilmListAdapter(films);
            upcomingMoviesRecycleView.setAdapter(upcomingMoviesAdapter);
//        }, error -> {
//            uLoading.setVisibility(View.GONE);
//            Log.i("MoviesApp", "sendRequestUpcomingMovies - onErrorResponse: " + error.toString());
//        });
//        uRequestQueue.add(uStringRequest);
    }

    private void banners() {
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.wide));
        sliderItems.add(new SliderItem(R.drawable.wide1));
        sliderItems.add(new SliderItem(R.drawable.wide3));

        viewPager.setAdapter(new SliderAdapter(sliderItems, viewPager));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.setCurrentItem(1);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(slideRunnable);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(slideRunnable, 2000);
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    private void initView() {
        viewPager = findViewById(R.id.sliderViewPager);

        bestMoviesRecycleView = findViewById(R.id.bestMoviesList);
        bestMoviesRecycleView.setLayoutManager(
            new LinearLayoutManager(
            this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        );
        bLoading = findViewById(R.id.bestMoviesLoading);

        categoryRecycleView = findViewById(R.id.categoryList);
        categoryRecycleView.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
        );
        cLoading = findViewById(R.id.categoryLoading);

        upcomingMoviesRecycleView = findViewById(R.id.upcomingMoviesList);
        upcomingMoviesRecycleView.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
        );
        uLoading = findViewById(R.id.upcomingMoviesLoading);
    }
}