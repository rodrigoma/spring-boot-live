package br.com.rodrigoma.rest;

import br.com.rodrigoma.database.TechTalk;
import br.com.rodrigoma.database.TechTalkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/techtalk")
public class DatabaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseController.class);

    @Autowired
    private TechTalkRepository techTalkRepository;

    @PostMapping(value = "/database", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity databasePost(@RequestParam("title") final String title,
                                       @RequestParam("date") final String date,
                                       @RequestParam("author") final String author,
                                       @RequestParam("category") final String category,
                                       @RequestParam("duration") final int duration) {

        LOGGER.info("DATABASE: Saving techtalk {} by {}", title, author);

        TechTalk techTalk = techTalkRepository.save(new TechTalk()
                .setTitle(title)
                .setDate(date)
                .setAuthor(author)
                .setCategory(category)
                .setDuration(duration));

        return ResponseEntity.ok(techTalk);
    }

    @GetMapping(value = "/database/byId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity databaseGetById(@RequestParam("id") final Long id) {
        TechTalk techTalk = techTalkRepository.findOne(id);

        LOGGER.info("DATABASE BY ID: Search by {}", id);

        return ResponseEntity.ok(techTalk);
    }

    @GetMapping(value = "/database/byAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity databaseGetByAuthor(@RequestParam("author") final String author) {
        List<TechTalk> techTalk = techTalkRepository.findByAuthor(author);

        LOGGER.info("DATABASE BY AUTHOR: Search by {}", author);

        return ResponseEntity.ok(techTalk);
    }

    @GetMapping(value = "/database/byDuration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity databaseGetByDuration(@RequestParam("duration") final int duration) {
        List<TechTalk> techTalk = techTalkRepository.findByDurationGreaterThan(duration);

        LOGGER.info("DATABASE BY DURATION: Search by {}", duration);

        return ResponseEntity.ok(techTalk);
    }

    @GetMapping(value = "/database/byCategoryDuration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity databaseGetByCategory(@RequestParam("category") final String category,
                                                @RequestParam("duration") final int duration) {
        List<TechTalk> techTalk = techTalkRepository.findByCategoryAndDurationGreaterThan(category, duration);

        LOGGER.info("DATABASE BY CATEGORY and DURATION: Search by [{},{}]", category, duration);

        return ResponseEntity.ok(techTalk);
    }
}