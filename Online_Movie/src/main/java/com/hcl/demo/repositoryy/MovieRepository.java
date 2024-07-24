package com.hcl.demo.repositoryy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.demo.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, String> {
    // Additional query methods if needed
	List<Movie> findByMovieNameContainingIgnoreCase(String movieName);
    List<Movie> findByCollectionBetween(int start, int end);
}

