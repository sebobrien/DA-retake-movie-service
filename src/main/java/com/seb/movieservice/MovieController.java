package com.seb.movieservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/movie-service")
public class MovieController {

	@Autowired
	private Environment env;

	@GetMapping(value = "/title/{title}")

	public String GetMovieByTitle(@PathVariable(value = "title") String title) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = String.format("https://www.omdbapi.com/?t=%s&apikey=%s", title, env.getProperty("OMDb.api-key"));
		System.out.println(uri);
		return restTemplate.getForObject(uri, String.class);	
		

	}

	@GetMapping("/id/{id}")
    public String GetMovieById(@PathVariable(value="id") String id ){

        RestTemplate restTemplate = new RestTemplate();
        String uri = String.format("https://www.omdbapi.com/?i=%s&apikey=%s", id , env.getProperty("OMDb.api-key") );
        return restTemplate.getForObject(uri,String.class);

    }
}
