package com.demoqa.models.books;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class AddBookRqModel {

    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}
