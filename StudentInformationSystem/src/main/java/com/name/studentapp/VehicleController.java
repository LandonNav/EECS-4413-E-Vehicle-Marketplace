package com.name.studentapp;

import jakarta.ws.rs.*; 

import jakarta.ws.rs.core.MediaType; 

import java.util.List; 

  

@Path("/vehicle") 

public class VehicleController { 

    private VehicleDAO studentDAO = new VehicleDAO(); 

  

    @GET 

    @Produces(MediaType.APPLICATION_JSON) 

    public List<Vehicle> getAllVehicles() { 

        return studentDAO.readAll(); 

    } 

  

    @POST 

    @Consumes(MediaType.APPLICATION_JSON) 

    @Produces(MediaType.APPLICATION_JSON) 

    public Vehicle createVehicle(Vehicle student) { 

        return studentDAO.create(student); 

    } 

  

    @GET 

    @Path("/{id}") 

    @Produces(MediaType.APPLICATION_JSON) 

    public Vehicle getVehicle(@PathParam("id") int id) { 

        return studentDAO.read(id); 

    } 

  

    @PUT 

    @Path("/{id}") 

    @Consumes(MediaType.APPLICATION_JSON) 

    @Produces(MediaType.APPLICATION_JSON) 

    public Vehicle updateStudent(@PathParam("id") int id, Vehicle student) { 

        return studentDAO.update(id, student); 

    } 

  

    @DELETE 

    @Path("/{id}") 

    @Produces(MediaType.APPLICATION_JSON) 

    public void deleteVehicle(@PathParam("id") int id) { 

        studentDAO.delete(id); 

    } 

} 