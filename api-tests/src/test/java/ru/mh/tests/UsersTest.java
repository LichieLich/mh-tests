package ru.mh.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.mh.api.conditions.Conditions;
import ru.mh.api.payloads.UserPayload;
import ru.mh.api.responses.UserRegistrationResponse;
import ru.mh.api.services.UserApiService;

import static org.hamcrest.Matchers.*;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private final Faker faker = new Faker();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://adv.mind-hub.ru/v1/";
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    @Test
    public void testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .login(faker.name().username())
                .password(faker.internet().password(8, 20));

        // expect
        userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", not(empty())));


//        UserRegistrationResponse response = userApiService.registerUser(user)
//                .asPojo(UserRegistrationResponse.class);
//        response.getId();
//        Any assertions
    }

}
