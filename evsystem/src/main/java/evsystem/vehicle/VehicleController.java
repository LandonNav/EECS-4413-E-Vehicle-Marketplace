package evsystem.vehicle;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List; 

@Path("/vehicles") 

public class VehicleController 
{ 
    private VehicleDAO vehicleDAO = new VehicleDAO(); 
 
    @GET 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Vehicle> getAllVehicles() 
    { 
        return vehicleDAO.readAll(); 
    } 

    @POST 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public void createStudent(Vehicle vehicle) 
    { 
        vehicleDAO.create(vehicle); 
    } 

    @GET 
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Vehicle getVehicle(@PathParam("id") int id) 
    { 
        return vehicleDAO.read(id); 
    } 

    @PUT 
    @Path("/{id}") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public void updateVehicle(@PathParam("id") int id, Vehicle vehicle) 
    { 
        vehicleDAO.update(id, vehicle); 
    } 

    @DELETE 
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public void deleteVehicle(@PathParam("id") int id) 
    { 
        vehicleDAO.delete(id); 
    }

    @GET
@Path("/filter")
@Produces(MediaType.APPLICATION_JSON)
public List<Vehicle> filterVehicles(
    @QueryParam("type") String type,
    @QueryParam("minPrice") Double minPrice,
    @QueryParam("maxPrice") Double maxPrice,
    @QueryParam("minRating") Integer minRating
) {
    return vehicleDAO.filterVehicles(type, minPrice, maxPrice, minRating);
}

} 
