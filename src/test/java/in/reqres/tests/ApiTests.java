package in.reqres.tests;

import in.reqres.models.AuthorizeRequestModel;
import in.reqres.models.AuthorizeResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static in.reqres.specs.AuthorizeSpec.*;
import static in.reqres.specs.RegistrationSpec.*;
import static in.reqres.specs.UsersSpec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class ApiTests {

    @Test
    @DisplayName("Получение списка пользователей")
    public void getUsersList() {
        given()
                .spec(successgetUserListRequest)
                .when()
                .get()
                .then()
                .spec(successGetUserListResponse)
                .extract();
    }

    @Test
    @DisplayName("Пользователи не найдены")
    public void userNotFound() {
        given()
                .spec(failedGetUserListRequest)
                .when()
                .get()
                .then()
                .spec(failedGetUserListResponse);

    }

    @Test
    @DisplayName("Успешная авторизация")
    public void successAuthorization() {
        AuthorizeRequestModel body = new AuthorizeRequestModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");
        AuthorizeResponseModel response = given()
                .spec(successRequestAuthorization)
                .body(body)
                .when()
                .post()
                .then()
                .spec(successResponseAuthorization)
                .extract()
                .as(AuthorizeResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Неуспешная авторизация")
    public void failedAuthorization() {
        AuthorizeRequestModel body = new AuthorizeRequestModel();
        body.setEmail("eve.holt@reqres.in");
        given()
                .spec(failedRequestAuthorization)
                .body(body)
                .when()
                .post()
                .then()
                .spec(failedResponseAuthorization)
                .extract();
    }

    @Test
    @DisplayName("Неуспешная регистрация")
    public void failedRegistration() {
        AuthorizeRequestModel body = new AuthorizeRequestModel();
        body.setEmail("sydney@fife");
        given()
                .spec(failedRequestRegistration)
                .body(body)
                .when()
                .post()
                .then()
                .spec(failedResponseRegistration)
                .extract();
    }
}
