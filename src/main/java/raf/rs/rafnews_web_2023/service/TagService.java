package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.model.Category;
import raf.rs.rafnews_web_2023.dto.CategoryDTO;
import raf.rs.rafnews_web_2023.repository.api.TagRepositoryAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TagService {


    public TagService(){
        System.out.println(this);
    }

    @Inject
    private TagRepositoryAPI tagRepository;



}
