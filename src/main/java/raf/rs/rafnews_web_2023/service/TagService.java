package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.repository.api.TagRepositoryAPI;

import javax.inject.Inject;

public class TagService {


    public TagService(){
        System.out.println(this);
    }

    @Inject
    private TagRepositoryAPI tagRepository;



}
