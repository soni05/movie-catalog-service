package com.catalog.movie.controller;

import com.catalog.movie.domain.CatalogItem;
import com.catalog.movie.domain.Movie;
import com.catalog.movie.domain.Ratings;
import com.catalog.movie.domain.UserRating;
import com.catalog.movie.services.MoviesCatalogService;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogController {

    @Autowired
    MoviesCatalogService moviesCatalogService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        List<CatalogItem> catalogItemList = new ArrayList<>();

       UserRating ratings = restTemplate.getForObject("http://ratings-service/rating/users/"+ userId , UserRating.class);


        return ratings.getUserRating().stream().map(ratings1 -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + ratings1.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Test", ratings1.getRating());
                }).collect(Collectors.toList());

        // getall rated moviediD

        // for each movie id call movie infor service and get details

        //
    }
}
