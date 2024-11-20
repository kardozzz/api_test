package com.demoqa.data;

import lombok.Data;

@Data
public class TestData {
    String userName = System.getProperty("userName","login"),
            password = System.getProperty("userPassword","password");
}