package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests extends TestBase {
    @Test
    void statusCode404Test() {
        given()
                .when()
                .get("api/users/23")
                .then()
                .statusCode(404);
    }
    @Test
    void singleUsersTest() {
        given()
                .when()
                .get("api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"));
    }
    @Test
    void successLoginTest() {
        given()
                .contentType(JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\"," +
                        " \"password\": \"cityslicka\" }")
                .when()
                .post("api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
