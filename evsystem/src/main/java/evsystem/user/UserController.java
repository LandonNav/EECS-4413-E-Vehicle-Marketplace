package evsystem.user;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List; 

@Path("/users") 

public class UserController 
{ 
    private UserDAO userDAO = new UserDAO(); 
 
    @GET 
    @Produces(MediaType.APPLICATION_JSON) 
    public List<User> getAllUsers() 
    { 
        return userDAO.readAll(); 
    } 

    @POST 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public void createStudent(User user) 
    { 
        userDAO.create(user); 
    } 

    @GET 
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public User getUser(@PathParam("id") int id) 
    { 
        return userDAO.read(id); 
    } 

    @PUT 
    @Path("/{id}") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public void updateUser(@PathParam("id") int id, User user) 
    { 
        userDAO.update(id, user); 
    } 

    @DELETE 
    @Path("/{id}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public void deleteUser(@PathParam("id") int id) 
    { 
        userDAO.delete(id); 
    }
} 