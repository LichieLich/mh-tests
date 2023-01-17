package ru.mh.ui.test;

import org.testng.annotations.Test;
import ru.mh.api.payloads.UserPayload;
import ru.mh.api.services.UserApiService;
import ru.mh.ui.pages.MainPage;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;


public class TestLogin extends BaseUiTest {

  private final UserApiService userApiService = new UserApiService();

  @Test
  public void userCanLoginWithValidCredentials() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);

    // then
    MainPage.open().loginAs(user.login(), user.password());
    MainPage.loginText.shouldHave(text(user.login()));
  }

  @Test
  public void userCantLoginWithWrongPassword() {
    MainPage.open().loginAs(faker.name().username(), faker.internet().password(8, 20));
    MainPage.errorText.shouldHave(text("Incorrect login or password"));
    MainPage.loginText.shouldBe(hidden);
  }

  // Тест будет всегда падать, так как в системе специально лежит такой дефект
  @Test
  public void userCantLoginWithoutPassword() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);

    // then
    MainPage.open().loginAs(user.login(), "");
    MainPage.errorText.shouldHave(text("Incorrect login or password"));
    MainPage.loginText.shouldBe(hidden);
  }
}
