package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "blog")

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;
    private String content;
    private String description;
    private String author;
    private String date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {}

    public Blog(Long id, String title, String content, String description, String author, String date, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
        this.author = author;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
