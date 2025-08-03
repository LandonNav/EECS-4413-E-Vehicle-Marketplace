package evsystem.cart;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("/cart")  // Base path for all cart-related endpoints
public class CartController {

    // 🔧 In-memory data store to simulate a cart system.
    // Key: userId, Value: list of vehicleIds they've added to their cart
    private static final Map<String, List<Integer>> cartDB = new HashMap<>();

    /**
     * Adds a vehicle to a user's cart.
     * Endpoint: POST /cart/add
     * Params (form): userId, vehicleId
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addToCart(@FormParam("userId") String userId,
                            @FormParam("vehicleId") int vehicleId) {
        // If the user doesn't have a cart yet, create one
        cartDB.putIfAbsent(userId, new ArrayList<>());

        List<Integer> userCart = cartDB.get(userId);

        // Add the vehicle only if it's not already in the cart
        if (!userCart.contains(vehicleId)) {
            userCart.add(vehicleId);
        }

        return "Vehicle " + vehicleId + " added to cart for user " + userId;
    }

    /**
     * ❌ Removes a vehicle from a user's cart.
     * Endpoint: POST /cart/remove
     * Params (form): userId, vehicleId
     */
    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeFromCart(@FormParam("userId") String userId,
                                 @FormParam("vehicleId") int vehicleId) {
        List<Integer> userCart = cartDB.get(userId);

        if (userCart != null && userCart.remove(Integer.valueOf(vehicleId))) {
            return "Vehicle " + vehicleId + " removed from cart for user " + userId;
        } else {
            return "Vehicle " + vehicleId + " not found in user " + userId + "'s cart.";
        }
    }

    /**
     * View the cart contents for a user.
     * Endpoint: GET /cart/view?userId=xyz
     * Returns: List of vehicleIds in the user's cart (JSON)
     */
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> viewCart(@QueryParam("userId") String userId) {
        return cartDB.getOrDefault(userId, Collections.emptyList());
    }
}
