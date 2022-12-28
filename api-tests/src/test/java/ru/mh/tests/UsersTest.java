package ru.mh.tests;

import org.testng.annotations.Test;
import ru.mh.api.conditions.Conditions;
import ru.mh.api.payloads.UserPayload;

import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseTest {

  @Test
  public void CanRegisterNewUser() {
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

  @Test
  public void CantRegisterExistingUser() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password(faker.internet().password(8, 20));

    userApiService.registerUser(user);

    // expected
    userApiService.registerUser(user)
        .shouldHave(Conditions.statusCode(422))
        .shouldHave(Conditions.bodyField("errors",
            hasItem(String.format("User with login %s already exists", user.login()))));
  }

}
