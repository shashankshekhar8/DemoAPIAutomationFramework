package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

@Listeners(com.api.listeners.TestListener.class)
public class LoginAPITest {

    @Test(description = "Verify if Login API is working...")
    public void loginTest(){
        /*Response response = given()
                .baseUri("http://64.227.160.186:8080")
                .header("Content-Type", "application/json")
                .body("{\"username\": \"uday1234\", \"password\": \"uday12345\"}")
                .post("/api/auth/login");

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.statusCode(), 200);*/

        LoginRequest loginRequest = new LoginRequest("shashank1234", "shashank1234");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);

        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getId());

        Assert.assertTrue(loginResponse.getToken() != null);
        Assert.assertEquals(loginResponse.getId(), 2042);
        Assert.assertEquals(loginResponse.getEmail(), "10shashank10@gmail.com");
    }
}
