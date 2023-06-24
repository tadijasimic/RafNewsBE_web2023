package raf.rs.rafnews_web_2023.resource;


import raf.rs.rafnews_web_2023.entity.Category;
import raf.rs.rafnews_web_2023.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {

        return Response.ok(categoryService.getAllCategories()).build();
    }

    @POST
    public Category addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @DELETE
    public void deleteCategory(Category category) {
        categoryService.deleteCategory(category);
    }

    @GET
    @Path("/id/{id}")
    public Category findById(@PathParam("id") int id) {
        return categoryService.searchCategoryById(id);
    }

    @GET
    @Path("name/{name}")
    public Category findByName(@PathParam("name") String name) {
        return categoryService.searchCategoryByName(name);
    }









}
