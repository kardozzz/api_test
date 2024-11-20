package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthRsModel {
    public static String userId, password, token, expires;
    private Boolean isActive;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("created_date")
    private String createdDate;

}
