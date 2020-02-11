package com.example.offilemovies.data.remote;

import com.example.offilemovies.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MovieApiService {

    @POST("movie/popular")
    Call<MoviesResponse>  loadPopularMovies();

}
