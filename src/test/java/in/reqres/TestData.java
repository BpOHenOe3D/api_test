package in.reqres;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestData {

    public final static int STATUS_CODE_200 = 200;
    public final static int STATUS_CODE_201 = 201;
    public final static int STATUS_CODE_400 = 400;
    public final static int STATUS_CODE_401 = 401;
    public final static int STATUS_CODE_404 = 404;

    @BeforeAll
    static void configureBeforeAll() {
        RestAssured.baseURI = "https://reqres.in/";
    }
}