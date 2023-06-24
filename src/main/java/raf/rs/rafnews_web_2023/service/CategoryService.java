package raf.rs.rafnews_web_2023.service;


import raf.rs.rafnews_web_2023.entity.Category;
import raf.rs.rafnews_web_2023.repository.api.CategoryRepositoryAPI;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepositoryAPI categoryRepository;


    public CategoryService(){
        System.out.println(this);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public Category addCategory(Category category) {
        return categoryRepository.addCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteCategory(category);
    }
    public Category searchCategoryByName(String name) {
        return categoryRepository.searchCategoryByName(name);
    }

    public Category searchCategoryById(int id) {
        return categoryRepository.searchCategoryById(id);
    }

}
