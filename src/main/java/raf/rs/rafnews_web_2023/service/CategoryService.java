package raf.rs.rafnews_web_2023.service;


import raf.rs.rafnews_web_2023.converter.CategoryDTO_Converter;
import raf.rs.rafnews_web_2023.converter.UserDTO_Converter;
import raf.rs.rafnews_web_2023.dto.category.CategoryDTO;
import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.model.Category;
import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.repository.api.CategoryRepositoryAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    @Inject
    CategoryRepositoryAPI categoryRepository;


    public CategoryService() {

    }

    public List<CategoryDTO> allCategories() {
        List<Category> categories = categoryRepository.allCategories();
        return CategoryDTO_Converter.convertToCategoryDTOList(categories);
    }

    public List<CategoryDTO> categoriesForPage(int pageIndex, int pageSize) {
        List<Category> categories = categoryRepository.categoriesForPage(pageIndex, pageSize);
        return CategoryDTO_Converter.convertToCategoryDTOList(categories);
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryDTO_Converter.convertToCategory(categoryDTO);
        category = categoryRepository.addCategory(category);
        return CategoryDTO_Converter.convertToCategoryDTO(category);
    }

    public void deleteCategory(CategoryDTO categoryDTO) {

        Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());
        categoryRepository.deleteCategory(category);
    }


    public CategoryDTO searchCategoryByName(String name) {
        return CategoryDTO_Converter.convertToCategoryDTO
                (categoryRepository.searchCategoryByName(name));
    }

    public CategoryDTO searchCategoryById(int categoryId) {
        return CategoryDTO_Converter.convertToCategoryDTO
                (categoryRepository.searchCategoryById(categoryId));
    }

    public List<CategoryDTO> searchNewsCategories(List<News> news) {
        List<CategoryDTO> dtoList = new ArrayList<>();
        for (News currNews : news) {
            dtoList.add(
                    searchCategoryById(currNews.getCategoryId())
            );
        }
        return dtoList;
    }


}
