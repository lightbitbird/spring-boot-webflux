package com.webflux.routing;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.domain.Person;
import com.webflux.handler.PersonHandler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonRouter {

    @Autowired
    private PersonHandler personHandler;

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/person"),
                route(GET("/list").and(accept(APPLICATION_JSON)), this::listAll)
                        .andRoute(GET("/list2").and(accept(APPLICATION_JSON)), this::findAllMono)
                        .andRoute(POST("/save").and(accept(APPLICATION_JSON)), this::save)
                        .andRoute(POST("/update/{id}").and(accept(APPLICATION_JSON)), this::update));
    }

    private Mono<ServerResponse> listAll(ServerRequest req) {
        return ok().body(personHandler.findAll(req), Person.class);
    }

    private Mono<ServerResponse> findAllMono(ServerRequest req) {
        return ok().body(BodyInserters.fromPublisher(personHandler.findAllMono(),
                new ParameterizedTypeReference<List<Person>>() {
                }));
    }

    private Mono<ServerResponse> save(ServerRequest req) {
        return ok().body(Flux.just(personHandler.save(req)), Person.class);
    }

    private Mono<ServerResponse> update(ServerRequest req) {
        return ok().body(Flux.just(personHandler.update(req)), Person.class);
    }

}
