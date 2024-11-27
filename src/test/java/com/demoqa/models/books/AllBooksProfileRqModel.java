package com.demoqa.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AllBooksProfileRqModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}

