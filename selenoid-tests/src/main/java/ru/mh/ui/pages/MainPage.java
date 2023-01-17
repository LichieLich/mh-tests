package ru.mh.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

  //  Navbar
  public static SelenideElement signIn = $(byText("SignIn"));
  public static SelenideElement loginText = $("span.navbar-text > .m-2:first-child");
  public static SelenideElement accountBalance = $("span.navbar-text > .m-2:nth-child(3)");
  public static SelenideElement certificatesBalance = $("span.navbar-text > .m-2:nth-child(5)");
  public static SelenideElement addAdvert = $(byText("Add advert"));

//  Login modal window
  public static SelenideElement loginInput = $("#login");
  public static SelenideElement passwordInput = $("#password");
  public static SelenideElement signInButton = $(".modal-footer").find(byText("Sign In"));
  public static SelenideElement errorText = $(".link-danger");

  // Advertisements
  public static ElementsCollection advs = $$(".card");

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
