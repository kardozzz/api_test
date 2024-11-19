package com.demoqa.models;

import lombok.Data;
import java.util.List;

@Data
public class GetBookListRsModel {
        String userId,
                username;
        List<BookModel> books;
    }
