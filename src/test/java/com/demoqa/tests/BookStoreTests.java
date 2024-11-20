package com.demoqa.tests;


import com.demoqa.api.AccountApi;
import com.demoqa.helpers.extentions.WithLogin;
import com.demoqa.models.AddBookRsModel;
import com.demoqa.models.GetBookListRqModel;
import com.demoqa.models.GetBookListRsModel;
import com.demoqa.models.IsbnModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.BookApi.*;
import static com.demoqa.api.AccountApi.getUserProfile;
import static com.demoqa.api.AccountApi.getUserProfileWithNotBook;
import static org.hamcrest.MatcherAssert.assertThat;

@Tag("API")
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Тесты с книгами")
    void successfulAuthorization() {
        GetBookListRsModel userProfile = getUserProfile();
        deleteAllBooksInProfile();
        addBooksInProfile();
        deleteBooksInProfile();
        getUserProfileWithNotBook().assertThat(userProfile.getBooks()).isEmpty();;


    }
}
