package com.demoqa.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthData {
    public static String userId, password, token, expires;
    private Boolean isActive;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("created_date")
    private String createdDate;

}
