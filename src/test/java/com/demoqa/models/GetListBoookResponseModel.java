package com.demoqa.models;

import lombok.Data;
import java.util.List;

@Data
public class GetListBoookResponseModel {
        String userId,
                username;
        List<BookModel> books;
    }
