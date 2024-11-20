package com.demoqa.models;

import lombok.Data;
import java.util.List;

@Data
public class GetBookListRqModel {
        String userId,
                username;
        List<IsbnModel> books;
    }
