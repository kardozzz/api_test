package com.demoqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class DataSpec{

public static RequestSpecification createRequestSpec = with()
        .filter(withCustomTemplates())
        .contentType(JSON)
        .log().all();

public static ResponseSpecification successfulResponse201Spec = new ResponseSpecBuilder()
        .expectStatusCode(201)
        .log(ALL)
        .build();

public static ResponseSpecification authUserResponse200Spec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .log(ALL)
        .build();

public static ResponseSpecification deleteBook204Spec = new ResponseSpecBuilder()
        .expectStatusCode(204)
        .log(ALL)
        .build();
public static RequestSpecification createBookStoreRequestSpec = with()
        .filter(withCustomTemplates())
        .log().all();

}
