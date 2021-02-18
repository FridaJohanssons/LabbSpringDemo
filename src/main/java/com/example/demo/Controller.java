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

//    @GetMapping("/hello")
//    public Optional<Movie> sayHello() {
//
//        movieRepository.save(new Movie(0,"Test","Test"));
//
//        return movieRepository.findById(1L);
//    }

    //Ett get request för att läsa all info från databasen.
    @GetMapping("/movies")
    public List<Movie> all() {
        return movieRepository.findAll();
    }

    //Ett get request för att läsa specefik info från databasen(info med id...).
    // Detta är koden nedan fast utan status.not_found 404. Vilken man ska ha!
//    @GetMapping ("/movies/{id}")
//    public Optional<Movie> one(@PathVariable Long id){
//        return movieRepository.findById(id);
//    }


    //Detta funkar men se längre ner för kortare kod

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
    //Kortare kod
//    @GetMapping("/movies/{id}")
//    public Movie one(@PathVariable Long id){
//        var result = movieRepository.findById(id);
//        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id "
//                + id + " not found"));
//    }

    //Ännu kortare kod, den är bra. Kastas exception om film med id.. inte finns.
//    @GetMapping("/movies/{id}")
//    public Movie one(@PathVariable Long id){
//        return movieRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id "
//                + id + " not found"));
//    }
    //Sista vertionen. Här tillhandahålls en film om det inte hittas en film.
    // Bra om man inte vill kasta en exeption utan istället skapa en tom film.
    @GetMapping("/movies/{id}")
    public Movie one(@PathVariable Long id){
        return movieRepository.findById(id)
                .orElse(new Movie());
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id "
//                        + id + " not found"));
    }

    //Post request skapar en ny film med 201 created medelande.
    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }


}
