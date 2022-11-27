package ru.mh.api.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiService {

    protected RequestSpecification setup() {
        return RestAssured
                .given()
                .auth().basic("mhuser", "mhtest")
                .contentType(ContentType.JSON).log().all();
    }
}
