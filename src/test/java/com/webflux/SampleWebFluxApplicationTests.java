/*
 * Copyright 2012-2017 the original author or authors.
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.webflux.MyWebFluxApplication;
import com.webflux.domain.Person;
import com.webflux.repositry.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Basic integration tests for WebFlux application.
 *
 * @author Brian Clozel
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { MyWebFluxApplication.class })
public class SampleWebFluxApplicationTests {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private PersonRepository repository;

//    @Before
//    public void setUp() {
//        this.hotelService = mock(HotelService.class);
//        when(hotelService.findOne(sampleUUID)).thenReturn(Mono.just(new Hotel(sampleUUID, "test")));
//        HotelHandler hotelHandler = new HotelHandler(hotelService);
//         
//        this.client = WebTestClient.bindToRouterFunction(ApplicationRoutes.routes(hotelHandler)).build();
//    }
    
    @Test
    public void testIndex() throws Exception {
        this.webClient.get().uri("/").accept(MediaType.TEXT_PLAIN).exchange().expectBody(String.class)
                .isEqualTo("Webflux works!");
    }

    @Test
    public void testList() throws Exception {
        Person person = new Person("Tom", "Hardy");
//        BDDMockito.given(Flux.fromIterable(this.repository.findAll())).willReturn(Flux.just(person));

        this.webClient.get().uri("/person/list")
                .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBodyList(Person.class)
                .hasSize(1).contains(person);
//                .isEqualTo(person);
    }

    @Test
    public void testSave() throws Exception {
        Person person = new Person("Tom", "Hardy");
        person.setId(7);

        BDDMockito.given(Flux.fromIterable(this.repository.findAll())).willReturn(Flux.just(person));

        this.webClient.post().uri("/person/save", new Person("Tom", "Hardy")).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(Person.class)
                .isEqualTo(person);
    }

    @Test
    public void testUpdate() throws Exception {
        this.webClient.post().uri("/person/update/{id}", "2", new Person("Tom", "Hardy"))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(Mono.just("Hello WebFlux!"), String.class).exchange().expectBody(String.class)
                .isEqualTo("Hello WebFlux!");
    }

    /**
     * private ExchangeFunction exchange = ExchangeFunctions.create(new
     * ReactorClientHttpConnector()); public void createPerson() { URI uri =
     * URI.create(String.format("http://%s:%d/person/save", "localhost", 0));
     * Person tom = new Person("Tom", "Hardy");
     * 
     * ClientRequest request = ClientRequest.method(HttpMethod.POST,
     * uri).body(BodyInserters.fromObject(tom)).build();
     * 
     * Mono<ClientResponse> response = exchange.exchange(request);
     * 
     * System.out.println(response.block().bodyToMono(Person.class).block().toString());
     * }
     **/

    @Test
    public void testEcho() throws Exception {
        this.webClient.post().uri("/echo").contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN)
                .body(Mono.just("Hello WebFlux!"), String.class).exchange().expectBody(String.class)
                .isEqualTo("Hello WebFlux!");
    }

    @Test
    public void testMultiStream() throws Exception {
        this.webClient.get().uri("/multiStream").accept(MediaType.TEXT_PLAIN).exchange().expectBody(String.class)
                .isEqualTo("Webflux works!");
    }

}
