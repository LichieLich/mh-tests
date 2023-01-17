package ru.mh.playwright.pages;

import com.microsoft.playwright.*;

public class MainPage {
  private final Page page;
  // navbar
  public final Locator signIn;
  public final Locator accountText;

  //login window
  public final Locator loginField;
  public final Locator passwordField;
  public final Locator signInButton;
  public final Locator errorText;

  public MainPage(Page page) {
    this.page = page;
    // navbar
    this.signIn = page.getByText("SignIn");
    this.accountText = page.locator("span.navbar-text > .m-2:first-child");

    // login window
    this.loginField = page.locator("#login");
    this.passwordField = page.locator("#password");
    this.signInButton = page.locator(".modal-footer").getByText("Sign In");
    this.errorText = page.locator(".link-danger");
  }

  public void navigate() {
    page.navigate("https://adv.mind-hub.ru/");
  }

  public void login(String login, String password) {
    signIn.click();
    loginField.fill(login);
    passwordField.fill(password);
    signInButton.click();
  }
}
