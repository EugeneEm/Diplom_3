import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;


public class UserClient {
    @Step("Удаление пользователя")
    public void userDelete(String token) {
         String response = given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Env.BASE_URL)
                .header("Authorization", token)
                .when()
                .delete(Env.USER_LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .body("success", is(true))
                .extract()
                .path("message");
        assertEquals(response, "User successfully removed");
    }

    @Step("Создание нового пользователя")
    public String userCreate(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Env.BASE_URL)
                .body(user)
                .when()
                .post(Env.USER_REGISTER)
                .then().log().all()
                .assertThat()
                .statusCode(HTTP_OK)
                .body("success", is(true))
                .extract()
                .path("accessToken");
    }
}
