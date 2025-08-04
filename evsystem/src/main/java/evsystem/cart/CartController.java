package evsystem.cart;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.*;

/**
 * CartController handles all cart-related operations using a temporary in-memory storage.
 * This version is ideal for project demos where persistence is not required.
 */
@Path("/cart") // Base path for cart operations: /webapi/cart
public class CartController {

    // Temporary in-memory data store
    // Key: userId, Value: list of vehicleIds in their cart
    private static final Map<String, List<Integer>> cartDB = new HashMap<>();

    /**
     * Adds a vehicle to a user's cart.
     * Endpoint: POST /webapi/cart/add
     * Form parameters: userId, vehicleId
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addToCart(@FormParam("userId") String userId,
                            @FormParam("vehicleId") int vehicleId) {

        // If user has no cart yet, create one
        cartDB.putIfAbsent(userId, new ArrayList<>());

        List<Integer> userCart = cartDB.get(userId);

        // Add vehicle if not already present
        if (!userCart.contains(vehicleId)) {
            userCart.add(vehicleId);
            return "Vehicle " + vehicleId + " added to cart for user " + userId;
        } else {
            return "Vehicle already in cart.";
        }
    }

    /**
     * Removes a vehicle from a user's cart.
     * Endpoint: POST /webapi/cart/remove
     * Form parameters: userId, vehicleId
     */
    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeFromCart(@FormParam("userId") String userId,
                                 @FormParam("vehicleId") int vehicleId) {

        List<Integer> userCart = cartDB.get(userId);

        if (userCart != null && userCart.remove(Integer.valueOf(vehicleId))) {
            return "Vehicle removed from cart.";
        } else {
            return "Vehicle not found in cart.";
        }
    }

    /**
     * Returns all vehicles in a user's cart.
     * Endpoint: GET /webapi/cart/view?userId=...
     * Response: JSON array of vehicleIds
     */
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> viewCart(@QueryParam("userId") String userId) {
        return cartDB.getOrDefault(userId, Collections.emptyList());
    }

    /**
     * Clears the user's cart and returns a success message.
     * Endpoint: POST /webapi/cart/checkout
     * Form parameter: userId
     */
    @POST
    @Path("/checkout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkout(@FormParam("userId") String userId) {
        List<Integer> items = cartDB.get(userId);

        if (items == null || items.isEmpty()) {
            return "{\"status\":\"error\",\"message\":\"Cart is empty or does not exist.\"}";
        }

        int count = items.size();

        // Clear the cart after checkout
        cartDB.remove(userId);

        return "{\"status\":\"success\",\"message\":\"Checkout completed with " + count + " item(s).\"}";
    }
}
