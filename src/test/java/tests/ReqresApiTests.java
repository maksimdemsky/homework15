package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresApiTests extends TestBase {
    @Test
    void statusCode404Check() {
        given()
                .when()
                .get("api/users/23")
                .then()
                .statusCode(404);
    }
    @Test
    void singleUsersCheck() {
        given()
                .when()
                .get("api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"));
    }
}
