package raf.rs.rafnews_web_2023.service;


import raf.rs.rafnews_web_2023.entity.Category;
import raf.rs.rafnews_web_2023.entity.dto.CategoryDTO;
import raf.rs.rafnews_web_2023.repository.api.CategoryRepositoryAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepositoryAPI categoryRepository;

    

    
    public CategoryService(){
        System.out.println(this);
    }

    public List<CategoryDTO> allCategories() {
        return buildListDTO(categoryRepository.allCategories());
    }

    public List<CategoryDTO> categoriesForPage(int pageIndex, int pageSize) {
        return buildListDTO(categoryRepository.categoriesForPage(pageIndex, pageSize));
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());
        return categoryRepository.addCategory(category).buildDTO();
    }

    public void deleteCategory(CategoryDTO categoryDTO) {

        Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());
        categoryRepository.deleteCategory(category);
    }
    public CategoryDTO searchCategoryByName(String name) {
        return categoryRepository.searchCategoryByName(name).buildDTO();
    }

    private List<CategoryDTO> buildListDTO(List<Category> categories) {
        List<CategoryDTO> DTOs = new ArrayList<>();
        for(Category category: categories){
            DTOs.add(category.buildDTO());
        }
        return DTOs;
    }
}
