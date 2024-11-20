package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookRqModel {
        String userId;
        List<IsbnModel> collectionOfIsbns;
    }

