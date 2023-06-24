package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.repository.api.CommentRepositoryAPI;

import javax.inject.Inject;

public class CommentService {

    @Inject
    CommentRepositoryAPI commentRepository;

    public CommentService() {
        System.out.println(this);
    }


}
