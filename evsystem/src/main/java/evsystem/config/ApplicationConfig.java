package evsystem.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

import evsystem.vehicle.VehicleController;
import evsystem.cart.CartController;
import evsystem.review.ReviewController; // ✅ Import review controller

/**
 * ApplicationConfig sets the base URI for all RESTful endpoints and registers resource classes.
 * This class extends jakarta.ws.rs.core.Application and specifies which resource classes 
 * (controllers) should be included in the JAX-RS application.
 */
@ApplicationPath("/webapi")  // All REST APIs will be accessible under this base URI
public class ApplicationConfig extends Application {

    /**
     * Registers all controller classes that define REST endpoints.
     *
     * @return Set of classes annotated with JAX-RS annotations
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Register REST resource classes
        resources.add(VehicleController.class); // Vehicle listing, filtering, etc.
        resources.add(CartController.class);    // Shopping cart (in-memory)
        resources.add(ReviewController.class);  // ✅ Review submission and retrieval

        return resources;
    }
}
