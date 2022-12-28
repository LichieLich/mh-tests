package ru.mh.tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeClass;
import ru.mh.api.ProjectConfig;
import ru.mh.api.services.UserApiService;

import java.util.Locale;

public class BaseTest {
  protected Faker faker;
  protected final UserApiService userApiService = new UserApiService();

  @BeforeClass
  public void setUp() {
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    faker = new Faker(new Locale(config.locale()));

    RestAssured.baseURI = config.baseUrl();
    RestAssured.registerParser("text/plain", Parser.JSON);
    // https://github.com/rest-assured/rest-assured/issues/684 Не работают логи для text данных
  }
}
