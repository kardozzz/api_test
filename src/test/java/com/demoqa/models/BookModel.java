package com.demoqa.models;

import lombok.Data;

@Data

public class BookModel {
    public String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher;
    int pages;
    String description, website;
}