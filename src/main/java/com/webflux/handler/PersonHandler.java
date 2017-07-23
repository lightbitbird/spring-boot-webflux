package com.webflux.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.webflux.domain.Person;
import com.webflux.repositry.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Mono<List<Person>> findAllMono() {
        return Mono.just(personRepository.findAll());
    }

    public Flux<Person> findAll(ServerRequest req) {
        return Flux.fromIterable(personRepository.findAll());
    }

    @Transactional
    public Person save(ServerRequest req) {
        return personRepository.save(req.bodyToMono(Person.class).block());
    }

    @Transactional
    public Person update(ServerRequest req) {
        System.out.println(" id -- " + req.pathVariable("id"));
        Person person = req.bodyToMono(Person.class).block();
        person.setId(Long.valueOf(req.pathVariable("id")));
        return personRepository.save(person);
    }

}
