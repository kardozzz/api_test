package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRsModel {
    String userId, password, token, expires;
    @JsonProperty("created_date")
    private String createdDate;
    @JsonProperty("username")
    private String userName;
    Boolean isActive;
}
