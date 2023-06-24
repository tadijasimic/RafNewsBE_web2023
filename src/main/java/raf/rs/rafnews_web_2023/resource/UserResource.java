package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.entity.User;
import raf.rs.rafnews_web_2023.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(userService.getAllUsers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {
        System.out.flush();
        return userService.addUser(user);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findByEmail(@PathParam("email") String email) {
        return userService.searchUserByEmail(email);
    }

    //done
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("id") int id) {
        return userService.searchUserById(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser(User user) {
        return userService.editUser(user);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }


}
