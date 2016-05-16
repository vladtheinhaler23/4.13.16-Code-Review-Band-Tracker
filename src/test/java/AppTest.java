import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @ClassRule
  public static DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#bandName").with("Drake");
    fill("#hometown").with("Toronto");
    submit("#bandSubmit");
    assertThat(pageSource()).contains("Drake");
    assertThat(pageSource()).contains("Toronto");
  }

  @Test
  public void venueIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#venueName").with("Roseland");
    fill("#city").with("Portland");
    submit("#venueSubmit");
    assertThat(pageSource()).contains("Roseland");
    assertThat(pageSource()).contains("Portland");
  }

  @Test
  public void bandIndexDisplaysName() {
    Band testBand = new Band("Drake", "Toronto");
    testBand.save();
    String url = String.format("http://localhost:4567/");
    goTo(url);
    assertThat(pageSource()).contains("Drake");
  }
}
