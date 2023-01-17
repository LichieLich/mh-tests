package ru.mh.playwright.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import ru.mh.api.payloads.UserPayload;
import ru.mh.api.services.UserApiService;
import ru.mh.playwright.pages.MainPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestLogin extends BaseTest {

  UserApiService userApiService = new UserApiService();

  @Test
  public void userCanLoginWithValidCredentials() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);

    //then
    Page page = context.newPage();
    MainPage mainPage = new MainPage(page);

    mainPage.navigate();
    mainPage.login(user.login(), user.password());

    assertThat(mainPage.accountText).containsText(user.login());
  }

  // Тест всегда падает из-за дефекта
  @Test
  public void userCantLoginWithoutPassword() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);

    // then
    Page page = context.newPage();
    MainPage mainPage = new MainPage(page);

    mainPage.navigate();
    mainPage.login(user.login(), "");

    assertThat(mainPage.errorText).containsText("Incorrect login or password");
    assertThat(mainPage.accountText).isHidden();
  }

  @Test
  public void userCantLoginWithWrongPassword() {
    // given
    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);

    // then
    Page page = context.newPage();
    MainPage mainPage = new MainPage(page);

    mainPage.navigate();
    mainPage.login(user.login(), "incorrectpassword");

    assertThat(mainPage.errorText).containsText("Incorrect login or password");
    assertThat(mainPage.accountText).isHidden();
  }
}
