package evsystem.vehicle;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * RESTful controller that exposes endpoints for managing Vehicle resources.
 * Handles CRUD operations, filtering, and sorting of vehicle listings.
 */
@Path("/vehicles") // Base URI path for all endpoints in this controller
public class VehicleController {

    // DAO instance to interact with the underlying database
    private VehicleDAO vehicleDAO = new VehicleDAO();

    /**
     * GET /vehicles
     * Returns a list of all vehicles in the database.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.readAll();
    }

    /**
     * POST /vehicles
     * Creates a new vehicle record in the database.
     * Accepts JSON input in the request body.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createStudent(Vehicle vehicle) {
        vehicleDAO.create(vehicle);
    }

    /**
     * GET /vehicles/{id}
     * Retrieves a single vehicle record by its ID.
     * @param id Vehicle identifier from the path
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vehicle getVehicle(@PathParam("id") int id) {
        return vehicleDAO.read(id);
    }

    /**
     * PUT /vehicles/{id}
     * Updates an existing vehicle record with new data.
     * @param id Vehicle identifier to update
     * @param vehicle JSON data representing the new state
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateVehicle(@PathParam("id") int id, Vehicle vehicle) {
        vehicleDAO.update(id, vehicle);
    }

    /**
     * DELETE /vehicles/{id}
     * Deletes a vehicle record from the database.
     * @param id Vehicle identifier to delete
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVehicle(@PathParam("id") int id) {
        vehicleDAO.delete(id);
    }

    /**
     * GET /vehicles/filter
     * Filters and sorts vehicle records based on optional query parameters.
     * Example: /vehicles/filter?type=SUV&minPrice=10000&maxPrice=30000&minRating=4&sortBy=price&order=asc
     *
     * @param type       Filter by vehicle type (optional)
     * @param minPrice   Minimum price filter (optional)
     * @param maxPrice   Maximum price filter (optional)
     * @param minRating  Minimum rating filter (optional)
     * @param sortBy     Field to sort by, e.g., "price", "rating" (optional)
     * @param order      Sorting order: "asc" or "desc" (optional)
     */
    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehicle> filterVehicles(
        @QueryParam("type") String type,
        @QueryParam("minPrice") Double minPrice,
        @QueryParam("maxPrice") Double maxPrice,
        @QueryParam("minRating") Integer minRating,
        @QueryParam("sortBy") String sortBy,
        @QueryParam("order") String order
    ) {
        return vehicleDAO.filterVehicles(type, minPrice, maxPrice, minRating, sortBy, order);
    }
}
