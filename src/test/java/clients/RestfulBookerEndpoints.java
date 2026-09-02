package clients;

public class RestfulBookerEndpoints {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String GET_BOOKINGS_ENDPOINT = "/booking";        // GET, all bookings or filtered by query params
    public static final String GET_BOOKING_ENDPOINT = "/booking/{id}";
    public static final String POST_BOOKING_ENDPOINT = "/booking";
    public static final String POST_AUTH = "/auth";
    public static final String DELETE_BOOKING_ENDPOINT = "/booking/{id}";
}
