package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.dto.request.LoginRequest;
import raf.rs.rafnews_web_2023.dto.response.Response;
import raf.rs.rafnews_web_2023.dto.user.UserDTO;
import raf.rs.rafnews_web_2023.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> allUsers() {
        return userService.allUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> usersForPage(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize){
        return userService.usersForPage(pageIndex, pageSize);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO findByEmail(@PathParam("email") String email) {
        return userService.searchUserByEmail(email);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO findById(@PathParam("id") int id) {
        return userService.searchUserById(id);
    }


    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO add(@Valid UserDTO user, @PathParam("id") int id) {

            return userService.addUser(user);
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@Valid UserDTO user, @PathParam("id") int id) {
        return userService.editUser(user);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @POST
    @Path("/signup")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup( UserDTO newUser) {
        return userService.signup(newUser);
    }
    @DELETE
    public void deleteUser(@Valid UserDTO user) {
        userService.deleteUser(user);
    }


}
