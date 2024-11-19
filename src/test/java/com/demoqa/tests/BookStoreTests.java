package com.demoqa.tests;


import com.demoqa.api.AccountApi;
import com.demoqa.helpers.extentions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.BookApi.getRandomBookIsbn;

@Tag("API")
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class BookStoreTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Тесты с книгами")
    void successfulAuthorization() {
        api.BookApi.deleteAllBooksInProfile();
        api.BookApi.addBookToProfile(getRandomBookIsbn());

        // 2. Получаем тот же ISBN и удаляем книгу из профиля
        api.BookApi.deleteBook(getRandomBookIsbn());

        // 3. Проверяем, что книга удалена
        api.BookApi.verifyBookIsDeleted(api.BookApi.getRandomBookIsbn());
    }
}
