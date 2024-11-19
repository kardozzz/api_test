package com.demoqa.models;

import lombok.Data;

@Data
public class DeleteBookRsModel {
    String userId,
            isbn,
            message;
    int code;
}
