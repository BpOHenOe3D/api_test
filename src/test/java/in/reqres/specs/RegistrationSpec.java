package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.is;

public class RegistrationSpec {

    public static RequestSpecification failedRequestRegistration = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification failedResponseRegistration = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("error", is("Missing password"))
            .build();
}
