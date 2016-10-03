package br.com.rodrigoma.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/techtalk")
public class RedisController {

    // TODO 03 List of Beans, StringRedisTemplate

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "/redis", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity redisPost(@RequestParam("title") final String title,
                                    @RequestParam("date") final String date) {
        String json = toJson(title, date);

        stringRedisTemplate.opsForHash().put("techtalk:schedules", title, date);

        return ResponseEntity.ok(json);
    }

    @GetMapping(value = "/redis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity redisGet(@RequestParam("title") final String title) {
        String date = (String) Optional
                .ofNullable(stringRedisTemplate.opsForHash().get("techtalk:schedules", title))
                .orElse("0000-00-00");

        return ResponseEntity.ok(toJson(title, date));
    }

    private String toJson(String title, String date) {
        return "{\"title\":\"" + title + "\"," +
                "\"date\":\"" + date + "\"}";
    }
}