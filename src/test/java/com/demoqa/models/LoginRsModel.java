package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRsModel {
    String userId, username, password, token, expires;
    @JsonProperty("created_date")
    private String createdDate;
    Boolean isActive;
}

