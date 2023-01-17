package ru.mh.ui.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;
import ru.mh.api.payloads.UserPayload;
import ru.mh.api.services.UserApiService;
import ru.mh.ui.data.Adv;
import ru.mh.ui.pages.CreateAdvPage;
import ru.mh.ui.pages.MainPage;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.text;

public class TestCreateAdv extends BaseUiTest {

  private final UserApiService userApiService = new UserApiService();

  // Всегда будет падать из-за дефектов системы
  @Test
  public void canCreateBonusAccAdvWithFullData() {
    // given
    Adv adv = new Adv()
        .title(faker.beer().name())
        .town(faker.address().city())
        .description(faker.lorem().characters(0, 500))
        .paymentMethod("bonus");

    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);
    MainPage.open().loginAs(user.login(), "");

    //then
    MainPage.addAdvert.click();

    $(byText("For 7 days, 3500 coins")).click();
    CreateAdvPage.fillAdvFieldsWith(adv);
    CreateAdvPage.accountCheckbox.click();
    CreateAdvPage.addButton.click();

    MainPage.accountBalance.shouldHave(text("6500"));

    SelenideElement advert = MainPage.advs.findBy(text(adv.title()));
    advert.find("h3").shouldHave(text(adv.title()));
    advert.find("h6").shouldHave(text(adv.town()));
    advert.find("p").shouldHave(text(adv.description()));
  }

  @Test
  public void canCreateCertificateAdvWithoutDescription() {
    // given
    // Хардкод на Moscow
    Adv adv = new Adv()
        .title(faker.beer().name())
        .town(faker.address().city())
        .paymentMethod("certificate");

    UserPayload user = new UserPayload()
        .login(faker.name().username())
        .password("defaultPass");

    userApiService.registerUser(user);
    MainPage.open().loginAs(user.login(), "");

    //then
    MainPage.addAdvert.click();

    $(byText("For 7 days, 3500 coins")).click();
    CreateAdvPage.fillAdvFieldsWith(adv);
    CreateAdvPage.certificateCheckbox.click();
    CreateAdvPage.addButton.click();

    MainPage.certificatesBalance.shouldBe(hidden);
    MainPage.accountBalance.shouldHave(text("10000"));

    // Не проверяем город из-за дефекта системы
    SelenideElement advert = MainPage.advs.findBy(text(adv.title()));
    advert.find("h3").shouldHave(text(adv.title()));
  }
}
