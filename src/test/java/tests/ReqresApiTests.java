package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

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

    @Test
    void updateUserTest() {
        given()
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\"," +
                        " \"job\": \"zion resident\" }")
                .when()
                .patch("api/users/2")
                .then()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));
    }

    @Test
    void deleteUserTest() {
        given()
                .when()
                .delete("api/users/2")
                .then()
                .statusCode(204)
                .body(is(emptyOrNullString()));
    }

    @Test
    void registerWithoutPasswordTest() {
        given()
                .contentType(JSON)
                .body("{ \"email\": \"sydney@fife\" }")
                .when()
                .post("api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
