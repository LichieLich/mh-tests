package ru.mh.tests;

import com.github.javafaker.Faker;
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
  }
}
