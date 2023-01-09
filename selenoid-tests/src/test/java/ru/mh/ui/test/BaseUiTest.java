package ru.mh.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class BaseUiTest {

  protected final Faker faker = new Faker();

  @BeforeSuite
  public void setUp() {
//    Запуск на локальном браузере без Selenoid
    Configuration.browser = "chrome";
//    Configuration.browser = "ru.mh.ui.SelenoidDriverProvider";
  }

  @AfterMethod
  public void tearDown() {
    Selenide.clearBrowserCookies();
  }

//  Для переключения пейджи без перехода по ссылке
  protected <T> T at(Class<T> pageClass) {
    return Selenide.page(pageClass);
  }
}
