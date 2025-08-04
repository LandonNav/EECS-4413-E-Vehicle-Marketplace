package evsystem.cart;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cart")
public class CartController {

    private final CartDAO cartDAO = new CartDAO();  // DAO handles persistence

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
        CartItem item = new CartItem(userId, vehicleId);
        boolean success = cartDAO.addToCart(item);

        if (success) {
            return "Vehicle " + vehicleId + " added to cart for user " + userId;
        } else {
            return "Vehicle " + vehicleId + " is already in the cart for user " + userId;
        }
    }

    /**
     * Removes a vehicle from a user's cart.
     * Endpoint: POST /cart/remove
     * Params (form): userId, vehicleId
     */
    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeFromCart(@FormParam("userId") String userId,
                                 @FormParam("vehicleId") int vehicleId) {
        boolean removed = cartDAO.removeFromCart(userId, vehicleId);

        if (removed) {
            return "Vehicle " + vehicleId + " removed from cart for user " + userId;
        } else {
            return "Vehicle " + vehicleId + " not found in user " + userId + "'s cart.";
        }
    }

    /**
     * View all vehicles in a user's cart.
     * Endpoint: GET /cart/view?userId=xyz
     */
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> viewCart(@QueryParam("userId") String userId) {
        return cartDAO.getCartVehicleIds(userId);
    }

    /**
     * Checkout the cart for a user.
     * Clears the cart after checkout.
     */
    @POST
    @Path("/checkout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkout(@FormParam("userId") String userId) {
        List<Integer> items = cartDAO.getCartVehicleIds(userId);

        if (items.isEmpty()) {
            return "{\"status\":\"error\",\"message\":\"Cart is empty or does not exist for user " + userId + "\"}";
        }

        int count = items.size();
        cartDAO.clearCart(userId);

        return "{\"status\":\"success\",\"message\":\"Checkout completed for user " + userId + " with " + count + " items.\"}";
    }
}
