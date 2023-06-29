package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.CategoryDTO;
import raf.rs.rafnews_web_2023.model.Category;

import java.util.ArrayList;
import java.util.List;

public abstract class CategoryDTO_Converter {

    private CategoryDTO_Converter(){

    }

    public static CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(category);
    }

    public static List<CategoryDTO> convertToCategoryDTOList(List<Category> categories) {
        List<CategoryDTO> dtoList = new ArrayList<>();
        for (Category category : categories) {
            dtoList.add(convertToCategoryDTO(category));
        }
        return dtoList;
    }
    public static Category convertToCategory(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName(), dto.getDescription());

    }




}
