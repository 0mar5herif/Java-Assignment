package com.omar.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.omar.util.ApiUtils;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class tApiTest {

    private static String userId;

    @BeforeClass
    public void setup() {
        ApiUtils.setupBaseUrl();
    }

    @Test(priority = 1)
    public void createUser() {
        String body = "{ \"name\": \"omar\", \"job\": \"tester\" }";
        Response response = ApiUtils.sendPostRequest("/api/users", body);
        System.out.println("POST Status Code: " +response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),201, "Expected status code 201");
        System.out.println("POST Response: "+ response.getBody().asString());
        userId = response.jsonPath().getString("id");
        System.out.println("Created User ID: " + userId);
        Assert.assertNotNull(userId, "Error: User ID returned null");
    }

    @Test(priority = 2)
    public void retrieveUser() {
        if (userId == null) {userId = "2";}
        Assert.assertNotNull(userId, "User ID is null. -- This might be due to running the test on its own");
        Response response = ApiUtils.sendGetRequest("/api/users/"+userId); //user ID isn't actually saved by reqres to it returns error here and test fails
        System.out.println("Status Code: "+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200,"Expected status code 200");
        System.out.println("Response: "+response.getBody().asString());
        String retrieveUserID = response.jsonPath().getString("data.id");
        Assert.assertEquals(retrieveUserID, userId, "User ID should match");
    }

    @Test(priority = 3)
    public void updateUser() {
        String body = "{ \"name\": \"omar\", \"job\": \"automation tester\" }";
        given()
            .contentType("application/json")
            .body(body)
            .when()
            .put("/api/users/2")
            .then()
            .statusCode(200);
    }
}
