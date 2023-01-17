package ru.mh.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class BaseUiTest {

  protected final Faker faker = new Faker();

  @BeforeSuite
  public void setUp() {
//    Запуск на локальном браузере без Selenoid
    Configuration.browser = "chrome";
//    Configuration.browser = System.getProperty("browser");
//    Configuration.browser = "ru.mh.ui.SelenoidDriverProvider";
  }

  @AfterMethod
  public void tearDown() {
    // Выполнение этого скрипта в Firefox работает, но вызывает ошибку. Поэтому завернул в try-catch  ¯\_(ツ)_/¯
    try {
      Selenide.executeJavaScript("Auth.resetAuth()");
    } catch (JavascriptException e) {}

    Selenide.clearBrowserCookies();
    Selenide.closeWebDriver();
  }

//  Для переключения пейджи без перехода по ссылке
  protected <T> T at(Class<T> pageClass) {
    return Selenide.page(pageClass);
  }
}
