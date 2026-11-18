package restassured_repeat;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
public class zad1 {
    private String json;
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
        Random random = new Random();
        String email = "something" + random.nextInt(10000000) + "@yandex.ru";
        json = "{\"email\": \"" + email + "\", \"password\": \"aaa\"}";
    }

    @Test
    public void registrationSuccessTest() {
        given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signup")
                .then()
                .statusCode(201);
    }
    @Test
    public void authSuccessTest() {
        given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signup");
        Response response = given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signin");
        response.then().assertThat()
                .body("token", notNullValue())
                .and()
                .statusCode(200);
    }
    @Test
    public void duplicateRegistrationTest() {
        given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signup");
        given()
                .header("Content-type", "application/json")
                .body(json)
                .post("/api/signup")
                .then()
                .statusCode(409);
    }
}
