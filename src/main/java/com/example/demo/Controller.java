package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller { //översätter http requests

    private MovieRepository movieRepository;

    @Autowired
    public Controller(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/hello")
    public Optional<Movie> sayHello() {


        movieRepository.save(new Movie(0,"Test","Test"));

        return movieRepository.findById(1L);
    }

    @GetMapping("/movies")
    public List<Movie> all() {
        return movieRepository.findAll();
    }

//    @GetMapping("/persons/{id}")
//    public ResponseEntity<Person> one(@PathVariable Long id){
//        var result = personRepository.findById(id);
//        if(result.isPresent())
//            return new ResponseEntity<Person>(result.get(), HttpStatus.OK);
//        return new ResponseEntity("User with"  + id + "nor found", HttpStatus.NOT_FOUND);
//
//
//    }


//    @GetMapping("/persons/{id}")
//    public Person one(@PathVariable Long id){
//
//        Optional<Person> optinalPerson = Optional.of(new Person(1,"",""));
//
//        var result = personRepository.findById(id);
//        if(result.isPresent())
//            return result.get();
//
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with"  + id + "nor found");
//
//
//    }
    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id){
        var result = movieRepository.findById(id);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with"
                + id + "nor found"));


    }
    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }


}
