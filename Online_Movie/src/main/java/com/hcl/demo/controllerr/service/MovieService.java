package com.hcl.demo.controllerr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.demo.entity.Movie;
import com.hcl.demo.repositoryy.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> getMovieById(String movieId) {
        return movieRepository.findById(movieId);
    }

    public List<Movie> getMoviesByName(String movieName) {
        return movieRepository.findByMovieNameContainingIgnoreCase(movieName);
    }

    public List<Movie> getMoviesByCollectionRange(int start, int end) {
        return movieRepository.findByCollectionBetween(start, end);
    }
   

   
}
