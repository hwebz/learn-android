package com.hado.moviesapp.Activities;

import static com.hado.moviesapp.Utils.DataUtils.readStreamText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hado.moviesapp.Adapters.ActorsListAdapter;
import com.hado.moviesapp.Adapters.DetailCategoryListAdapter;
import com.hado.moviesapp.Domains.Endpoints;
import com.hado.moviesapp.Domains.FilmDetail;
import com.hado.moviesapp.Domains.Films;
import com.hado.moviesapp.R;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView movieTitle, movieStars, movieDuration, movieSummary, movieActors;
    private int filmID;
    private ImageView moviePoster, backBtn;
    private RecyclerView.Adapter adapterActorList, adapterCategory;
    private RecyclerView actorRecyclerView, categoryRecyclerView;
    private NestedScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        filmID = getIntent().getIntExtra("id", 0);
        initView();
        sendRequestMovieDetail();
    }

    private void sendRequestMovieDetail() {
//        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        // mStringRequest = new StringRequest(Request.Method.GET, Endpoints.MOVIE_DETAIL + filmID, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            // From res/raw/best_movies.json
            FilmDetail detail = gson.fromJson(
                    readStreamText(getResources().openRawResource(R.raw.movie_detail)),
                    FilmDetail.class
            );
            // From API
            // FilmDetail detail = gson.fromJson(response, FilmDetail.class);

            Glide.with(DetailActivity.this)
                    .load(detail.getPoster())
                    .into(moviePoster);

            movieTitle.setText(detail.getTitle());
            movieStars.setText(detail.getImdbRating());
            movieDuration.setText(detail.getRuntime());
            movieSummary.setText(detail.getPlot());
            movieActors.setText(detail.getActors());

            if (detail.getImages() != null) {
                adapterActorList = new ActorsListAdapter(detail.getImages());
                actorRecyclerView.setAdapter(adapterActorList);
            }
            if (detail.getGenres() != null) {
                adapterCategory = new DetailCategoryListAdapter(detail.getGenres());
                categoryRecyclerView.setAdapter(adapterCategory);
            }
//        }, error -> {
//            finish();
//            Toast.makeText(DetailActivity.this, "Something wrong when fetching film information. Please try again later", Toast.LENGTH_SHORT).show();
//            Log.i("MoviesApp", "sendRequestMovieDetail - onErrorResponse = " + error.toString());
//        });
//        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        movieTitle = findViewById(R.id.movieTitle);
        scrollView = findViewById(R.id.detailScrollView);
        moviePoster = findViewById(R.id.moviePoster);
        progressBar = findViewById(R.id.progressBar);
        movieStars = findViewById(R.id.movieStars);
        movieDuration = findViewById(R.id.movieDuration);
        movieSummary = findViewById(R.id.movieSummary);
        movieActors = findViewById(R.id.movieActors);
        backBtn = findViewById(R.id.backBtn);
        categoryRecyclerView = findViewById(R.id.categoriesRecycler);
        actorRecyclerView = findViewById(R.id.actorsRecycler);
        actorRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backBtn.setOnClickListener(view -> finish());
    }
}