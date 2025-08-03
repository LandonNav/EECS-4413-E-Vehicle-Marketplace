package evsystem.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

@Path("/auth")
public class UserController {
    private UserDAO userDAO = new UserDAO();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        try {
            if (userDAO.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
                return jsonError(Response.Status.BAD_REQUEST, "Username or email already exists.");
            }
            if (user.getPassword() == null || user.getPassword().length() < 6) {
                return jsonError(Response.Status.BAD_REQUEST, "Password must be at least 6 characters.");
            }
            String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPasswordHash(hash);
            userDAO.create(user);
            return jsonOk("Registration successful!");
        } catch (Exception e) {
            return jsonError(Response.Status.INTERNAL_SERVER_ERROR, "Registration failed. Please try again.");
        }
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signin(User loginRequest, @Context HttpServletRequest request) {
        try {
            User user = userDAO.findByUsernameOrEmail(loginRequest.getUsername());
            if (user != null && BCrypt.checkpw(loginRequest.getPassword(), user.getPasswordHash())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getId());
                return jsonOk("Signed in");
            } else {
                return jsonError(Response.Status.UNAUTHORIZED, "Invalid username or password.");
            }
        } catch (Exception e) {
            return jsonError(Response.Status.INTERNAL_SERVER_ERROR, "Login failed. Please try again.");
        }
    }

    @POST
    @Path("/signout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signout(@Context HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) session.invalidate();
            return jsonOk("Signed out.");
        } catch (Exception e) {
            return jsonError(Response.Status.INTERNAL_SERVER_ERROR, "Signout failed.");
        }
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response status(@Context HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean signedIn = (session != null && session.getAttribute("userId") != null);
        String json = "{\"signedIn\":" + signedIn + "}";
        return Response.ok(json).type(MediaType.APPLICATION_JSON).build();
    }

    private Response jsonOk(String msg) {
        return Response.ok("{\"message\":\"" + msg + "\"}")
                .type(MediaType.APPLICATION_JSON).build();
    }

    private Response jsonError(Response.Status status, String err) {
        return Response.status(status)
                .entity("{\"error\":\"" + err + "\"}")
                .type(MediaType.APPLICATION_JSON).build();
    }
}
