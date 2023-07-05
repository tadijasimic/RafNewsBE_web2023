package raf.rs.rafnews_web_2023;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.rs.rafnews_web_2023.repository.api.*;
import raf.rs.rafnews_web_2023.repository.implementation.*;
import raf.rs.rafnews_web_2023.service.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@ApplicationPath("/api")
public class RafNewsApp extends ResourceConfig {

//c
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
                bindAsContract(UserService.class);
                bindAsContract(CategoryService.class);
                bindAsContract(CategoryService.class);
                bindAsContract(TagService.class);
                bindAsContract(CommentService.class);
                bindAsContract(NewsService.class);


            }
        };

        register(binder);
        packages("raf.rs.rafnews_web_2023");
    }

    @Provider
    public static class CorsFilter implements ContainerResponseFilter {
        @Override
        public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
            responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        }
    }
}