package com.demoqa.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void openPage() {
        open("/profile");
    }

    public static void deleteUiBook() {
        $("#delete-record-undefined").scrollTo().click();
        $("#closeSmallModal-ok").scrollTo().click();
    }

    public static void checkThatTheBookDeletedUI() {
        $("#see-book-Git Pocket Guide").shouldNotBe(visible);
    }


}
