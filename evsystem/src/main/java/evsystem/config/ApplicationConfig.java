package evsystem.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

import evsystem.vehicle.VehicleController;
import evsystem.cart.CartController;

/**
 * ApplicationConfig is the configuration class for the JAX-RS application.
 * It defines the base URI for REST endpoints and registers all available resource classes.
 */
@ApplicationPath("/webapi") // All REST endpoints will be accessible under /webapi
public class ApplicationConfig extends Application {

    /**
     * Returns a set of all resource classes to be included in the application.
     * These classes contain RESTful endpoints which will be made accessible to the client.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Register all REST controllers here so that JAX-RS recognizes them
        resources.add(VehicleController.class); // Controller for vehicle-related endpoints
        resources.add(CartController.class);    // Controller for shopping cart functionality

        return resources;
    }
}
