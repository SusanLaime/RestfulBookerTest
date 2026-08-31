package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;

/**
 * Encapsulates every request the tests make against the /booking endpoint -
 * the API equivalent of a Page Object. Test classes call these methods
 * instead of building RestAssured requests directly, the same way UI tests
 * call page methods instead of touching WebDriver/By locators directly.
 */
public class BookingClient {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public BookingClient() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response getAllBookings() {
        return RestAssured
                .when().get("/booking");
    }

    public Response getBookingsByFirstName(String firstname) {
        return RestAssured.given()
                .queryParam("firstname", firstname)
                .when().get("/booking");
    }

    public Response getBookingsByDates(String checkin, String checkout) {
        return RestAssured.given()
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .when().get("/booking");
    }

    /** Valid booking creation - Booking is serialized to JSON automatically (Jackson is on the classpath). */
    public Response createBooking(Booking booking) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(booking)
                .when().post("/booking");
    }

    /**
     * Sends a raw JSON body instead of a Booking object.
     * Used by the negative test cases, whose payloads intentionally don't
     * match the Booking model (a missing field, or a field with the wrong type).
     */
    public Response createBookingRaw(String rawJsonBody) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(rawJsonBody)
                .when().post("/booking");
    }
}
