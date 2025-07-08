package manager;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static utils.BaseApi.*;
import static utils.PropertiesReader.*;

public class AuthenticationController {

    public Response requestRegLogin(User user, String url) {
        return given()
                .baseUri(getProperty("login.properties", "baseUri"))
                .contentType(ContentType.JSON)//Content-type: App/JSON
                .body(user)
                .when()
                .post(url)
                .thenReturn();
    }
}


