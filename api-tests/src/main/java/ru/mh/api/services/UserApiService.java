package ru.mh.api.services;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import ru.mh.api.assertions.AssertableResponse;
import ru.mh.api.payloads.UserPayload;

public class UserApiService extends ApiService {

    @Step
    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("register/"));
    }

    @Step
    public AssertableResponse login(UserPayload user) {
        return new AssertableResponse(setup()
            .body(user)
            .when()
            .post("auth/"));
    }
}
