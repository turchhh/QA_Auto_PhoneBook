package api_tests;

import dto.ErrorMessageDto;
import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import static utils.RandomUtils.*;

public class RegistrationTestsRest extends AuthenticationController implements BaseApi {

    @Test
    public void registrationPositiveTest_200(){
        User user = new User(generateEmail(10), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void registrationPositiveTest_getBody() {
        User user = new User(generateEmail(10), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.body().print());
    }

    @Test
    public void registrationNegativeTest_wrongEmail_400(){
        User user = new User(generateString(10), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void loginPositiveTest_200(){
        User user = new User("valeriya.qa@gmail.com", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_401(){
        User user = new User("valeriya_qa@gmail.com", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 401);
    }

    @Test
    public void loginNegativeTest_ErrorMessage_401(){
        User user = new User("valeriya_qa@gmail.com", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        Assert.assertEquals(errorMessageDto.getError(), "Unauthorized", "validate error");
        System.out.println(response.getBody().asString());
    }

}
