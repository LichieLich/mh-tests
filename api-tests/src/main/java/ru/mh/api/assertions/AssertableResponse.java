package ru.mh.api.assertions;

import io.qameta.allure.Step;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.mh.api.conditions.Condition;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

  private final Response response;

  @Step("API response should have: {condition}")
  public AssertableResponse shouldHave(Condition condition) {
    log.info("Checking condition: {}", condition);
    condition.check(response);
    return this;
  }

    public <T> T asPojo(Class<T> tClass) {
    return response.as(tClass);
  }

  public Headers headers() {
    return response.getHeaders();
  }
}
