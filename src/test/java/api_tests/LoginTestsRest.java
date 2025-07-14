package api_tests;

import dto.ErrorMessageDto;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import java.time.LocalDate;


public class LoginTestsRest extends AuthenticationController implements BaseApi {

    SoftAssert softAssert = new SoftAssert();

    @Test(groups = "smoke")
    public void loginPositiveTest_200(){
        User user = new User("valeriya.qa@gmail.com", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginPositiveTest_Schema_200(){
        User user = new User("valeriya.qa@gmail.com", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        softAssert.assertEquals(response.getStatusCode(), 200);
        TokenDto tokenDto = response.getBody().as(TokenDto.class);
        System.out.println(tokenDto);
        softAssert.assertTrue(tokenDto.toString().contains("token"));
        softAssert.assertAll();
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
    public void loginNegativeTest_WrongPassword_401(){
        User user = new User("valeriya.a@gmail.com", "678512Lera");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertTrue(errorMessageDto.getMessage().equals("Login or Password incorrect"));
        softAssert.assertTrue(errorMessageDto.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDto.getPath().contains("login/usernamepassword"));
        System.out.println(LocalDate.now().toString());
        System.out.println(errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertEquals(LocalDate.now().toString(),errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertAll();
    }

    @Test
    public void loginNegativeTest_wrongEmailFormat_401(){
        User user = new User("valeriya.qa@gmail", "678512Lera!");
        Response response = requestRegLogin(user, LOGIN_URL);
        System.out.println(response.getStatusLine());
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertTrue(errorMessageDto.getMessage().equals("Login or Password incorrect"));
        softAssert.assertTrue(errorMessageDto.getError().equals("Unauthorized"));
        softAssert.assertTrue(errorMessageDto.getPath().contains("login/usernamepassword"));
        System.out.println(LocalDate.now().toString());
        System.out.println(errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertEquals(LocalDate.now().toString(),errorMessageDto.getTimestamp().substring(0,10));
        softAssert.assertAll();
    }
}
