package com.webflux.router;

import com.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by Jimmy. 2018/4/18  11:41
 */
@Configuration
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> helloRouterFunction(HelloHandler helloHandler) {

       /* return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        helloHandler::hello);*/
      //  return route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), helloHandler::hello);
        return route(GET("/hello"), helloHandler::hello);
    }
}
