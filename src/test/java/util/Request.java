package util;

import clients.RestfulBookerEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class Request {

    public static Response get(String endpoint){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.when().get(endpoint);
        return response;
    }

    /** Same as get(endpoint), but with query params - used for the firstname/checkin/checkout filters. */
    public static Response get(String endpoint, Map<String, String> queryParams){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().queryParams(queryParams)
                .when().get(endpoint);
        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().pathParam("id", id)
                .when().get(endpoint);
        return response;
    }

    public static Response post(String endpoint, String payload){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .when().post(endpoint);
        return response;
    }

    public static Response put(String endpoint, String id, String payload){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .and().pathParam("id", id)
                .when().put(endpoint);
        return response;
    }

    public static Response delete(String endpoint, String id, String token){
        RestAssured.baseURI = RestfulBookerEndpoints.BASE_URL;
        Response response = RestAssured.given().cookie("token", token).pathParam("id", id)
                .when().delete(endpoint);
        return response;
    }
}