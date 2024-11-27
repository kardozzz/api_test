package com.demoqa.tests;

import com.demoqa.api.BookApi;
import com.demoqa.helpers.extentions.WithLogin;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.demoqa.data.AuthData.*;

@Tag("API")
@DisplayName("Tests for book store")
public class BookStoreTests extends TestBase {

    @DisplayName("Delete book from UI")
    @Test
    @WithLogin
    void deleteBook() {

        BookApi booksApi = new BookApi();
        booksApi.deleteAllBooks();

        String isbn = booksApi.getRandomIsbn();
        booksApi.addBook(isbn);

        ProfilePage profilePage = new ProfilePage();
        profilePage
                .openPage()
                .checkAuthData(USER_NAME)
                .checkBookInProfile(isbn)
                .deleteBook(isbn)
                .checkResultOnUi(isbn);

        booksApi.checkResultOnApi();
    }

}
