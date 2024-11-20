package com.demoqa.data;

import lombok.Data;

@Data
public class TestData {
    String userName = System.getProperty("userName"),
            password = System.getProperty("password");
}