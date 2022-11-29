package ru.mh.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.mh.api.ProjectConfig;
import ru.mh.api.conditions.Conditions;
import ru.mh.api.payloads.UserPayload;
import ru.mh.api.services.UserApiService;

import java.util.Locale;

import static org.hamcrest.Matchers.*;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeClass
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));

        RestAssured.baseURI = config.baseUrl();
        RestAssured.registerParser("text/plain", Parser.JSON);
        // https://github.com/rest-assured/rest-assured/issues/684 Не работают логи для text данных
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
