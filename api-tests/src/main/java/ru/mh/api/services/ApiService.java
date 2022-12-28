package ru.mh.api.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.mh.api.ProjectConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiService {

  private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

  protected RequestSpecification setup() {
    return RestAssured
        .given()
        .auth().basic(config.apiLogin(), config.apiPassword())
        .contentType(ContentType.JSON)
        .filters(getFilters());
  }

  private List<Filter> getFilters() {

    if (config.logging()) {
      return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }

    return Collections.singletonList(new AllureRestAssured());
  }
}
