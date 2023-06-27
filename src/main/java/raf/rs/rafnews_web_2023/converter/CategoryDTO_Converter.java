package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.CategoryDTO;
import raf.rs.rafnews_web_2023.model.entity.Category;

public abstract class CategoryDTO_Converter {

    private CategoryDTO_Converter(){

    }

    public CategoryDTO convertToCategoryDTO(Category category) {
        return new CategoryDTO(category);
    }




}
