package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.dto.CategoryDTO;
import raf.rs.rafnews_web_2023.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDTO> allCategories() {
        return categoryService.allCategories();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDTO> usersForPage(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        return categoryService.categoriesForPage(pageIndex, pageSize);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDTO findByName(@PathParam("name") String name) {
        return categoryService.searchCategoryByName(name);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDTO addCategory(CategoryDTO category) {
        return categoryService.addCategory(category);
    }

    @DELETE
    public void deleteCategory(CategoryDTO category) {
        categoryService.deleteCategory(category);
    }


}
