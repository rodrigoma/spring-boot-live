package br.com.rodrigoma.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techtalk")
public class ListenerController {

    // TODO 06 RabbitTemplate to send

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/listener", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listenerGet(@RequestParam("date") final String date) {
        String json = toJson("Teste", date);

        rabbitTemplate.convertAndSend(
                "moip",
                "tech.talk.secondary.example",
                json);

        return ResponseEntity.ok(json);
    }

    @PostMapping(value = "/listener", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listenerPost(@RequestParam("title") final String title,
                               @RequestParam("date") final String date) {
        String json = toJson(title, date);

        rabbitTemplate.convertAndSend(
                "moip",
                "tech.talk.main.example",
                json);

        return ResponseEntity.ok(json);
    }

    private String toJson(String title, String date) {
        return "{\"title\":\"" + title + "\"," +
                "\"date\":\"" + date + "\"}";
    }
}