package clients;

public class BookingPayloads {

    public static final String BOOKING_WITHOUT_FIRST_NAME =
            "{"
                    + "\"lastname\": \"Doe\","
                    + "\"totalprice\": 150,"
                    + "\"depositpaid\": true,"
                    + "\"bookingdates\": {"
                    + "\"checkin\": \"2026-01-01\","
                    + "\"checkout\": \"2026-01-05\""
                    + "},"
                    + "\"additionalneeds\": \"Breakfast\""
                    + "}";

    public static final String BOOKING_WITH_INVALID_TOTAL_PRICE =
            "{"
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

    private BookingPayloads() {
        // Prevent object creation
    }
}