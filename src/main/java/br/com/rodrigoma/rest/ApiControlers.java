package br.com.rodrigoma.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techtalk")
public class ApiControlers {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@RequestParam("date") final String date) {

        return ResponseEntity.ok(toJson("Teste", date));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity post(@RequestParam("title") final String title,
                               @RequestParam("date") final String date) {

        return ResponseEntity.ok(toJson(title, date));
    }

    private String toJson(String title, String date) {
        return "{\"title\":\"" + title + "\"," +
                "\"date\":\"" + date + "\"}";
    }
}