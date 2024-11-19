package com.demoqa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.demoqa.helpers.CustonAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DataSpec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();


    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification response204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();
    public static ResponseSpecification response201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();



}
