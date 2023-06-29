package raf.rs.rafnews_web_2023;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.rs.rafnews_web_2023.repository.api.*;
import raf.rs.rafnews_web_2023.repository.implementation.*;
import raf.rs.rafnews_web_2023.service.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class RafNewsApp extends ResourceConfig {


    public RafNewsApp() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
             protected void configure() {

                bind(UserRepository.class).to(UserRepositoryAPI.class).in(Singleton.class);
                bind(CategoryRepository.class).to(CategoryRepositoryAPI.class).in(Singleton.class);
                bind(TagRepository.class).to(TagRepositoryAPI.class).in(Singleton.class);
                bind(CommentRepository.class).to(CommentRepositoryAPI.class).in(Singleton.class);
                bind(NewsRepository.class).to(NewsRepositoryAPI.class).in(Singleton.class);


                bindAsContract(UserService.class);
                bindAsContract(UserService.class);
                bindAsContract(UserService.class);
                bindAsContract(CategoryService.class);
                bindAsContract(TagService.class);
                bindAsContract(CommentService.class);
                bindAsContract(NewsService.class);


            }
        };

        register(binder);
        packages("raf.rs.rafnews_web_2023");
    }
}