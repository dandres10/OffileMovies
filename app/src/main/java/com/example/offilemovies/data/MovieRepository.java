package com.example.offilemovies.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.offilemovies.app.MyApp;
import com.example.offilemovies.data.local.MovieRoomDatabase;
import com.example.offilemovies.data.local.dao.MovieDao;
import com.example.offilemovies.data.local.entity.MovieEntity;
import com.example.offilemovies.data.network.NetworkBoundResource;
import com.example.offilemovies.data.network.Resource;
import com.example.offilemovies.data.remote.ApiConstants;
import com.example.offilemovies.data.remote.MovieApiService;
import com.example.offilemovies.data.remote.RequestInterceptor;
import com.example.offilemovies.data.remote.model.MoviesResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository() {
        //local > room
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContext(),
                MovieRoomDatabase.class,
                "db_movies"
        ).build();
        movieDao = movieRoomDatabase.getMovieDao();


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


    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){
        //tipo que devuelve room, tipo que devuelve directamente la api  con retrofit
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                    movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                // los datos que se tiene en room
                return movieDao.loadMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                // obtenemos los datos de la api remota
               return  movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }

}
