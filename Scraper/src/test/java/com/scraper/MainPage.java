package com.scraper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// https://www.jetbrains.com/
public class MainPage {
  public SelenideElement seeAllToolsButton = $("a.wt-button_mode_primary");
  public SelenideElement toolsMenu = $x("//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']");
  public SelenideElement searchButton = $("[data-test='site-header-search-action']");
}
