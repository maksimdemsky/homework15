package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReqresApiTests extends TestBase {
    @Test
    void statusCode404Check() {
        given()
                .when()
                .get("api/users/23")
                .then()
                .statusCode(404);
    }
}
