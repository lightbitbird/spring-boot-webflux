/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webflux;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import com.webflux.domain.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Client {

    private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());

    public static void main(String[] args) throws Exception {
        Client client = new Client();
//        client.createPerson();
        client.printAllPeople();
    }

    public void printAllPeople() {
        System.out.println("-----------------------printAllPeople1--------------------------------------------");
        WebClient client = WebClient.create(String.format("http://%s:%d", "localhost", 8080));
        // ClientRequest request = ClientRequest.method(HttpMethod.GET, uri).build();

        Flux<Person> people = client.get().uri("/person/list").accept(MediaType.APPLICATION_JSON).exchange()
                .flatMapMany(response -> response.bodyToFlux(Person.class));

        // 他候補Collection取得方法
        List<Person> peoples = people.buffer().toStream().findAny().orElse(new ArrayList<Person>());
        peoples.forEach(f -> System.out.println("f --> " + f));

//        Mono<List<Person>> peopleList = people.collectList();
//        peopleList.block().forEach(p -> System.out.println(p));
        System.out.println("-----------------------printAllPeople2--------------------------------------------");
    }

    /**
    public void printAllPeople() {
        URI uri = URI.create(String.format("http://%s:%d/person/list", "localhost", 8080));
        ClientRequest request = ClientRequest.method(HttpMethod.GET, uri).build();
        System.out.println("-----------------------printAllPeople1--------------------------------------------");

        Flux<Person> people = exchange.exchange(request).flatMapMany(response -> response.bodyToFlux(Person.class));
        
        System.out.println("-----------------------printAllPeople2--------------------------------------------");

        // 他候補Collection取得方法
        people.buffer().toStream().forEach(f -> System.out.println("f --> " + f));

//        Mono<List<Person>> peopleList = people.collectList();
//        System.out.println(peopleList.block());
    }
    **/

    public void createPerson() {
        URI uri = URI.create(String.format("http://%s:%d/person/save", "localhost", 8080));
        System.out.println("---------------------------createPerson1----------------------------------------");
        Person jack = new Person("Michael", "Jackson");

        ClientRequest request = ClientRequest.method(HttpMethod.POST, uri).body(BodyInserters.fromObject(jack)).build();
        Mono<ClientResponse> response = exchange.exchange(request);
        System.out.println("---------------------------createPerson2----------------------------------------");
        System.out.println(response.block().statusCode());
    }

}
