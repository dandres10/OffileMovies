package com.example.offilemovies.data.remote;

import com.example.offilemovies.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MoviesResponse>  loadPopularMovies();

}
