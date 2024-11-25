package com.demoqa.tests;


import api.BookApi;
import com.demoqa.api.AccountApi;
import com.demoqa.helpers.extentions.WithLogin;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;



@Tag("API")
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Тесты с книгами")
    void successfulAuthorization() {
        BookApi.deleteAllBooksInProfile();
        AccountApi.getUserProfile();
        BookApi.addBooksInProfile();
        ProfilePage.openPage();
        ProfilePage.deleteCertainBook();
        ProfilePage.checkThatTheBookDeletedUI();




    }
}
