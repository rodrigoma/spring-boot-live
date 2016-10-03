package br.com.rodrigoma.database;

import javax.persistence.*;

@Entity
@Table(name = "techtalk")
public class TechTalk {

    // TODO 03 A Common entity

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String date;
    private String author;
    private String category;
    private int duration;

    public Long getId() {
        return id;
    }

    public TechTalk setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TechTalk setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDate() {
        return date;
    }

    public TechTalk setDate(String date) {
        this.date = date;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public TechTalk setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public TechTalk setCategory(String category) {
        this.category = category;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public TechTalk setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String toJson() {
        return "{\"title\":\"" + title + "\"," +
                "\"author\":\"" + author + "\"," +
                "\"category\":\"" + category + "\"," +
                "\"duration\":" + duration + "," +
                "\"date\":\"" + date + "\"}";
    }
}