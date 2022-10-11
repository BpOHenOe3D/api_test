package in.reqres;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;

public class ApiTests extends TestData {

    @Test
    @DisplayName("Получение списка пользователей")
    public void getUsersList() {
        String uri = "api/users?page=2";
        given()
                .log().all()
                .when()
                .get(uri)
                .then()
                .log().all()
                .statusCode(STATUS_CODE_200)
                .body("data[0]", hasKey("id"));
    }

    @Test
    @DisplayName("Пользователи не найдены")
    public void userNotFound() {
        String uri = "api/users/23";
        given()
                .log().all()
                .when()
                .get(uri)
                .then()
                .log().all()
                .statusCode(STATUS_CODE_404);
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void successAuthorization() {
        JSONObject loginRequest = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "cityslicka");
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginRequest.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Неуспешная авторизация")
    public void failedAuthorization() {
        JSONObject failedLoginRequest = new JSONObject()
                .put("email", "eve.holt@reqres.in");
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(failedLoginRequest.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Неуспешная регистрация")
    public void failedRegistration() {
        JSONObject failedRegistration = new JSONObject()
                .put("email", "sydney@fife");
        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(failedRegistration.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }
}
