package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.hasKey;

public class UsersSpec {
    public static RequestSpecification successgetUserListRequest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("api/users?page=2")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);


    public static ResponseSpecification successGetUserListResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("data[0]", hasKey("id"))
            .build();

    public static RequestSpecification failedGetUserListRequest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("api/users/23")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);
    public static ResponseSpecification failedGetUserListResponse = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
