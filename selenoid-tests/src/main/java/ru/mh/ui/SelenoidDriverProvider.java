package ru.mh.ui;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidDriverProvider implements WebDriverProvider {

  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    DesiredCapabilities browser = new DesiredCapabilities();
    browser.setBrowserName("firefox");
    browser.setVersion("108.0");
    browser.setCapability("enableVNC", true);

    try {
      RemoteWebDriver driver = new RemoteWebDriver(
          URI.create("http://192.168.0.33:4444/wd/hub").toURL(),
          browser
      );
      driver.manage().window().setSize(new Dimension(1280, 1024));
      driver.setFileDetector(new LocalFileDetector());
      return driver;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
