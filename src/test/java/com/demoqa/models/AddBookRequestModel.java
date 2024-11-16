package com.demoqa.models;

import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {
        String userId;
        List<BookModel> collectionOfIsbns;
    }

