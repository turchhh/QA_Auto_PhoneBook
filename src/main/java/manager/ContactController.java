package manager;

import dto.Contact;
import dto.TokenDto;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.getProperty;
import static utils.BaseApi.*;

public class ContactController {

    protected TokenDto tokenDto;

    @BeforeSuite
    public void login(){
        User user = new User(getProperty("login.properties", "email"), getProperty("login.properties", "password"));
        Response response = new AuthenticationController().requestRegLogin(user, LOGIN_URL);
        if (response.getStatusCode() == 200){
            tokenDto = response.body().as(TokenDto.class);
        }else{
            System.out.println("Smth sent wrong -->" + response.getStatusCode());
        }
    }

    protected Response addNewContactRequest(Contact contact,TokenDto tokenDto){
        return given()
                .body(contact)
                .baseUri(getProperty("login.properties", "baseUri"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", tokenDto.getToken())
                .post(ADD_NEW_CONTACT_URL)
                .thenReturn();
    }

    public Response getAllUserContacts(){
        return given()
                .baseUri(getProperty("login.properties", "baseUri"))
                .accept(ContentType.JSON)
                .header("Authorization", tokenDto.getToken())
                .get(ADD_NEW_CONTACT_URL)
                .thenReturn();

    }


}
