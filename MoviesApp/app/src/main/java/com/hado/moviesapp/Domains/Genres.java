package com.hado.moviesapp.Domains;

import java.util.List;

public class Genres {
    private List<Genre> genres;

    public Genres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
