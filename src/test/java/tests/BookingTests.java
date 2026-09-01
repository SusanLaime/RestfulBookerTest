package tests;

import clients.BookingClient;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Response response = bookingClient.getAllBookings();

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", not(empty()));
    }

    @Test
    public void test02_GetBookingsByFirstName() {
        Response response = bookingClient.getBookingsByFirstName("Sally");

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", isA(List.class));
    }

    @Test
    public void test03_GetBookingsByDates() {
        Response response = bookingClient.getBookingsByDates("2014-03-13", "2014-05-21");

        response.then().assertThat().statusCode(200);
        response.then().log().body();
        response.then().assertThat().body("$", isA(List.class));
    }

    @Test
    //Expected Bad Request
    public void test04_CreateBookingWithoutFirstName() {
        // Intentionally missing the required "firstname" field
        String payloadWithoutFirstName = "{"
                //+ "\"firstname\": \"Doe\","
                + "\"lastname\": \"Doe\","
                + "\"totalprice\": 150,"
                + "\"depositpaid\": true,"
                + "\"bookingdates\": {"
                + "\"checkin\": \"2026-01-01\","
                + "\"checkout\": \"2026-01-05\""
                + "},"
                + "\"additionalneeds\": \"Breakfast\""
                + "}";

        Response response = bookingClient.createBookingRaw(payloadWithoutFirstName);
        response.then().log().body();

        // Expected: the API should reject a booking that is missing a required field.
        // NOTE: restful-booker is a demo API that is known to skip input validation on several
        // fields - if this assertion fails with a 200, that itself documents the gap. Adjust the
        // expected status code here to match whatever your decision table/first-partial defined.
        response.then().assertThat().statusCode(400);
    }

    /*@Test
    public void test05_CreateBookingWithInvalidTotalPrice() {

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        String payloadWithInvalidTotalPrice = "{"
                + "\"firstname\": \"John\","
                + "\"lastname\": \"Doe\","
                + "\"totalprice\": \"abc\","
                + "\"depositpaid\": true,"
                + "\"bookingdates\": {"
                + "\"checkin\": \"2026-01-01\","
                + "\"checkout\": \"2026-01-05\""
                + "},"
                + "\"additionalneeds\": \"Breakfast\""
                + "}";

        Response response = bookingClient.createBookingRaw(payloadWithInvalidTotalPrice);

        response.then().assertThat().statusCode(400);
    }*/

    @Test
    public void test05_CreateBookingWithInvalidTotalPrice() {

        // Enable full logging for debugging
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        String payloadWithInvalidTotalPrice = "{"
                + "\"firstname\": \"John\","
                + "\"lastname\": \"Doe\","
                + "\"totalprice\": \"abc\","
                + "\"depositpaid\": true,"
                + "\"bookingdates\": {"
                + "\"checkin\": \"2026-01-01\","
                + "\"checkout\": \"2026-01-05\""
                + "},"
                + "\"additionalneeds\": \"Breakfast\""
                + "}";

        Response response = bookingClient.createBookingRaw(payloadWithInvalidTotalPrice);

        // Expect 200 because Restful Booker does NOT validate totalprice
        response.then().statusCode(400);
    }



}
