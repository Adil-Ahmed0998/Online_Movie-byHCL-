package com.hcl.demo.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.demo.controllerr.service.MovieService;
import com.hcl.demo.entity.Movie;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/searchByIdForm")
    public String searchByIdForm() {
        return "searchByIdForm";
    }

    @GetMapping("/searchByNameForm")
    public String searchByNameForm() {
        return "searchByNameForm";
    }

    @GetMapping("/searchByCollectionForm")
    public String searchByCollectionForm() {
        return "searchByCollectionForm";
    }

    @GetMapping("/searchById")
    public String searchById(@RequestParam String movieId, Model model) {
        if (movieId == null || movieId.isEmpty()) {
            model.addAttribute("error", "Movie ID must not be empty");
            return "movieDetails";
        }

        Optional<Movie> movie = movieService.getMovieById(movieId);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
        } else {
            model.addAttribute("error", "Sorry, no matches found for the search criteria");
        }
        return "movieDetails";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam String movieName, Model model) {
        if (movieName == null || movieName.isEmpty()) {
            model.addAttribute("error", "Movie name must not be empty");
            return "movieDetails";
        }

        List<Movie> movies = movieService.getMoviesByName(movieName);
        if (!movies.isEmpty()) {
            model.addAttribute("movies", movies);
        } else {
            model.addAttribute("error", "Sorry, no matches found for the search criteria");
        }
        return "movieDetails";
    }

    @GetMapping("/searchByCollection")
    public String searchByCollection(@RequestParam int start, @RequestParam int end, Model model) {
        List<Movie> movies = movieService.getMoviesByCollectionRange(start, end);
        if (!movies.isEmpty()) {
            model.addAttribute("movies", movies);
        } else {
            model.addAttribute("error", "Sorry, no matches found for the search criteria");
        }
        return "movieDetails";
    }

    @GetMapping("/rent")
    public String rent(@RequestParam String movieId, HttpSession session) {
        session.setAttribute("movieId", movieId);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        // Skip validation and directly proceed to the payment page
        session.setAttribute("user", userId);
        return "redirect:/proceed";
    }

    @GetMapping("/proceed")
    public String proceed(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "proceed";
    }

    @GetMapping("/payment")
    public String payment(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "payment";
    }

    // Other methods if needed
}
