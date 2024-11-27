package com.demoqa.models.books;

import lombok.Data;

import java.util.List;

@Data
public class AllBooksProfileRsModel {
        private String userId, username;
        private List<BookModel> books;
    }

