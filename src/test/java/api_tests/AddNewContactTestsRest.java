package api_tests;

import dto.Contact;
import dto.ErrorMessageDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import io.restassured.response.Response;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;

import static utils.RandomUtils.generateEmail;
import static utils.RandomUtils.generateString;

public class AddNewContactTestsRest extends ContactController implements BaseApi {
    SoftAssert softAssert = new SoftAssert();

    @Test(groups = "smoke")
    public void addNewContactPositiveTest(){
        Contact contact = Contact.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        Response response = addNewContactRequest(contact, tokenDto);
        System.out.println(response.getStatusLine());
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        if (response.getStatusCode() == 200){
            responseMessageDto = response.body().as(ResponseMessageDto.class);
        }
        System.out.println(responseMessageDto.toString());
        Assert.assertTrue(responseMessageDto.getMessage().contains("Contact was added!"));
    }

    @Test
    public void addNewContactNegativeTest_wrongToken(){
        Contact contact = Contact.builder()
                .name(generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        Response response = addNewContactRequest(contact, TokenDto.builder()
                .token("wrong")
                .build());
        System.out.println(response.getStatusLine());
        ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto.toString());
        softAssert.assertEquals(errorMessageDto.getStatus(), 401);
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("JWT strings must contain exactly 2 period characters."));
        softAssert.assertTrue(errorMessageDto.getError().equals("Unauthorized"));
        softAssert.assertAll();
    }
}
