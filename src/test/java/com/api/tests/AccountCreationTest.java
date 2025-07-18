package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCreationTest {

    @Test(description = "Verify if SignUp API is working...")
    public void createAccountTest(){
        SignUpRequest signUpRequest = new SignUpRequest.Builder()
                .username("shashank1234")
                .email("10shashank10@gmail.com")
                .firstName("shashank")
                .lastName("shekhar")
                .password("shashank1234")
                .mobileNumber("77777777774")
                .build();

        AuthService authService = new AuthService();
        Response response = authService.signUp(signUpRequest);
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
