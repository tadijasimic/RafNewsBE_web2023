package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;

import javax.inject.Inject;

public class NewsService {
    @Inject
    NewsRepositoryAPI newsRepository;
    public NewsService(){
        System.out.println(this);
    }

}
