package ru.mh.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.mh.ui.data.Adv;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreateAdvPage {

  // Creation form
  public static SelenideElement townInput = $("input#town");
  public static SelenideElement titleInput = $("input#title");
  public static SelenideElement descriptionInput = $("textarea#description");
  public static SelenideElement phoneAccountCheckbox = $("input#pm-sms");
  public static SelenideElement accountCheckbox = $("input#pm-account");
  public static SelenideElement certificateCheckbox = $("input#pm-certificate");
  public static SelenideElement addButton = $(byText("Add"));


  public static CreateAdvPage open() {
    MainPage.open();
    return new CreateAdvPage();
  }

  public static void fillAdvFieldsWith(Adv adv) {
    townInput.setValue(adv.town());
    titleInput.setValue(adv.title());
    descriptionInput.setValue(adv.description());
  }

}
