package ru.mh.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

  //  Navbar
  public static SelenideElement signIn = $(byText("SignIn"));
  public static SelenideElement loginText = $("span.navbar-text > .m-2:first-child");

//  Login modal window
  public static SelenideElement loginInput = $("#login");
  public static SelenideElement passwordInput = $("#password");
  public static SelenideElement signInButton = $(".modal-footer").find(byText("Sign In"));
  public static SelenideElement errorText = $(".link-danger");

  @Step
  public static MainPage open() {
    Selenide.open("https://adv.mind-hub.ru/",
        "",
        "mhuser",
        "mhtest");
    return new MainPage();
  }

  @Step
  public void loginAs(String login, String password) {
    signIn.click();
    loginInput.setValue(login);
    passwordInput.setValue(password);
    signInButton.click();
  }
}
