package com.omar.util;

import com.omar.config.Config;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiUtils {

    public static void setupBaseUrl() {
        RestAssured.baseURI = Config.getBaseUrl; //"https://reqres.in";
    }
    public static Response sendPostRequest(String endpoint, String body) {
        //used try because response had that as a requirement that exceptions are handled
        try {
            Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
            return response;
        } catch (Exception e) {
            System.err.println("Error occurred while sending POST request: "+e.getMessage());
            return null;
        }
    }
    public static Response sendGetRequest(String endpoint) {
        try {
            Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
            return response;
        } catch (Exception e) {
            System.err.println("Error occurred while sending GET request: "+e.getMessage());
            return null;
        }
    }
    public static Response sendUpdateRequest(String endpoint, String body) {
        try {
            Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
            return response;
        } catch (Exception e) {
            System.err.println("Error occurred while sending Update request: "+e.getMessage());
            return null;
        }
    }
}
