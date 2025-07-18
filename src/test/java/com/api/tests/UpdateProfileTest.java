package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.ProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest {

    @Test
    public void updateProfileTest(){
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("shashank1234", "shashank1234"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());

        System.out.println("--------------------------------------------------------------------------");

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response = userProfileManagementService.getProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(), "shashank1234");

        System.out.println("--------------------------------------------------------------------------");

        ProfileRequest profileRequest = new ProfileRequest.Builder()
                .firstName("Disha")
                .lastName("Bhatt")
                .email("10shashank10@gmail.com")
                .mobileNumber("7777777771")
                .build();
        response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(userProfileResponse.getFirstName(), "Disha");
        Assert.assertEquals(userProfileResponse.getMobileNumber(), "7777777771");
        Assert.assertEquals(userProfileResponse.getLastName(), "Bhatt");
    }
}
