package com.example.offilemovies.data.remote.model;

import com.example.offilemovies.data.local.MovieEntity;

import java.util.List;

public class MoviesResponse {


    private List<MovieEntity> results;


    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
