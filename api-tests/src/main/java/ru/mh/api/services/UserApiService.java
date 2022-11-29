package ru.mh.api.services;

import ru.mh.api.assertions.AssertableResponse;
import ru.mh.api.payloads.UserPayload;

public class UserApiService extends ApiService {

    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("register/"));
    }
}
