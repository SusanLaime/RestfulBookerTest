package models;

/**
 * Represents a valid POST /booking payload.
 * Used for the positive-path requests. Negative test cases (missing/invalid
 * fields) are sent as raw JSON strings instead, since a malformed payload
 * by definition doesn't fit this model - see BookingClient.createBookingRaw().
 */
public class Booking {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public Booking() {
    }

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid,
                    BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}
