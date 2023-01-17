package ru.mh.playwright.test;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

  protected Browser browser;
  protected BrowserContext context;
  protected Faker faker = new Faker();

  @BeforeSuite
  public void setUp() {
    Playwright playwright = Playwright.create();
      browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome"));
  }

  @BeforeMethod
  public void setContext() {
    context = browser.newContext(
        new Browser.NewContextOptions()
            .setHttpCredentials("mhuser", "mhtest")
    );
  }

  @AfterMethod
  public void closeSession() {
    context.close();
  }

  @AfterSuite
  public void closeBrowser() {
    browser.close();
  }
}
