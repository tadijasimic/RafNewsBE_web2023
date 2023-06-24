package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.entity.Category;
import raf.rs.rafnews_web_2023.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> allCategories() {

        return categoryService.getAllCategories();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category addCategory(Category category) {
        return categoryService.addCategory(category);
    }

    @DELETE
    public void deleteCategory(Category category) {
        categoryService.deleteCategory(category);
    }



    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category findByName(@PathParam("name") String name) {
        return categoryService.searchCategoryByName(name);
    }









}
