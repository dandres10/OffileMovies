package com.example.offilemovies.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.offilemovies.data.MovieRepository;
import com.example.offilemovies.data.local.entity.MovieEntity;
import com.example.offilemovies.data.network.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private final LiveData<Resource<List<MovieEntity>>> popularMovies;
    private MovieRepository movieRepository;

    public MovieViewModel(){
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){
        return popularMovies;
    }
}
