package br.com.rodrigoma.rest;

import br.com.rodrigoma.elastic.ElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techtalk")
public class ElasticController {

    // TODO 04 Rest for elastic slide

    @Autowired
    private ElasticRepository elasticRepository;

    @PostMapping(value = "/elastic", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity elasticPost(@RequestParam("title") final String title,
                                      @RequestParam("date") final String date) {
        String json = toJson(title, date);

        elasticRepository.saveElastic(json);

        return ResponseEntity.ok(json);
    }

    @GetMapping(value = "/elastic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity elasticGet(@RequestParam("title") final String title) {
        String es = elasticRepository.getElastic(title);

        return ResponseEntity.ok(es);
    }

    private String toJson(String title, String date) {
        return "{\"title\":\"" + title + "\"," +
                "\"date\":\"" + date + "\"}";
    }
}