package com.example.offilemovies.data;

import com.example.offilemovies.data.remote.ApiConstants;
import com.example.offilemovies.data.remote.MovieApiService;
import com.example.offilemovies.data.remote.RequestInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;

    public MovieRepository() {
        //requestinterceptor: incluir en la cabecera el api_key
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();


        //remote retrofit
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(cliente)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        movieApiService = retrofit.create(MovieApiService.class);

    }
}
