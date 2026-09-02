package tests;

import clients.BookingClient;
import clients.RestfulBookerEndpoints;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static clients.BookingPayloads.BOOKING_WITHOUT_FIRST_NAME;
import static clients.BookingPayloads.BOOKING_WITH_INVALID_TOTAL_PRICE;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class BookingTests {

    private BookingClient bookingClient;

    @BeforeEach
    public void setUp() {
        bookingClient = new BookingClient();
    }

    @Test
    public void test01_GetAllBookings() {
        Response response = Request.get(RestfulBookerEndpoints.GET_BOOKINGS_ENDPOINT);

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", not(empty()));
    }

    @Test
    public void test02_GetBookingsByFirstName() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("firstname", "Sally");

        Response response = Request.get(RestfulBookerEndpoints.GET_BOOKINGS_ENDPOINT, queryParams);

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", isA(List.class));
    }

    @Test
    public void test03_GetBookingsByDates() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("checkin", "2014-03-13");
        queryParams.put("checkout", "2014-05-21");

        Response response = Request.get(RestfulBookerEndpoints.GET_BOOKINGS_ENDPOINT, queryParams);

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", isA(List.class));
    }

    @Test
    //Expected Bad Request
    public void test04_CreateBookingWithoutFirstName() {
        // Intentionally missing the required "firstname" field
        String payloadWithoutFirstName = BOOKING_WITHOUT_FIRST_NAME;

        Response response = bookingClient.createBookingRaw(payloadWithoutFirstName);
        response.then().log().body();

        // Expected 400 Bad Request, gotten 500 internal server error
        response.then().assertThat().statusCode(400);
    }


    @Test
    public void test05_CreateBookingWithInvalidTotalPrice() {

        // Enable full logging for debugging
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        String payloadWithInvalidTotalPrice = BOOKING_WITH_INVALID_TOTAL_PRICE;

        Response response = bookingClient.createBookingRaw(payloadWithInvalidTotalPrice);

        // Expect 200 because Restful Booker does NOT validate totalprice, originally 418 due to permission
        response.then().statusCode(400);
    }



}
