package api;

import com.demoqa.data.AuthData;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

import static com.demoqa.data.AuthData.userId;
import static com.demoqa.data.AuthData.token;
import static com.demoqa.specs.DataSpec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class BookApi {


    @Step
    public static void deleteAllBooksInProfile() {
        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)

                .when()
                .delete("/BookStore/v1/Books")

                .then()
                .spec(response204);
    }

    @Step("Получить список всех книг")
    public static List<String> getAllBookIsbns() {
        return given()
                .when()
                .get("BookStore/v1/Books") // GET запрос для получения всех книг
                .then()
                .spec(response200) // Проверка успешного ответа
                .extract()
                .jsonPath()
                .getList("books.isbn", String.class); // Извлекаем список ISBN всех книг
    }

    @Step("Выбрать случайную книгу из списка ISBN")
    public static String getRandomBookIsbn() {
        List<String> isbns = getAllBookIsbns(); // Получаем список ISBN
        assertThat(isbns).isNotEmpty(); // Проверяем, что список книг не пустой
        return isbns.get(new Random().nextInt(isbns.size())); // Выбираем случайный ISBN
    }

    @Step("Добавить выбранную книгу в профиль")
    public static void addBookToProfile(String isbn) {
        String bookJson = String.format("{\"isbn\": \"%s\", \"userId\": \"%s\"}", isbn, AuthData.userId); // Создаём JSON с ISBN и userId

        given()
                .header("Authorization", "Bearer " + AuthData.token) // Указываем токен для авторизации
                .queryParam("UserId", AuthData.userId) // Указываем ID пользователя
                .body(bookJson) // Передаём данные книги
                .when()
                .post("BookStore/v1/Books") // Отправляем POST запрос для добавления книги в профиль
                .then()
                .spec(response201); // Проверяем, что книга была добавлена успешно
    }

    @Step("Удалить книгу с ISBN {isbn}")
    public static void deleteBook(String isbn) {
        given()
                .header("Authorization", "Bearer " + AuthData.token) // Указываем токен для авторизации
                .queryParam("UserId", AuthData.userId) // Указываем ID пользователя
                .body("{ \"isbn\": \"" + isbn + "\" }") // Передаём ISBN книги в теле запроса
                .when()
                .delete("BookStore/v1/Book") // DELETE запрос для удаления книги
                .then()
                .spec(response204); // Проверка успешного удаления
    }

    @Step("Проверить, что книга с ISBN {isbn} удалена")
    public static void verifyBookIsDeleted(String isbn) {
        List<String> isbns = getAllBookIsbns(); // Получаем обновлённый список ISBN
        assertThat(isbns).doesNotContain(isbn); // Проверяем, что ISBN удалённой книги отсутствует
    }
}




