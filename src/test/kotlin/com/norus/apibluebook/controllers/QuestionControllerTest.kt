package com.norus.apibluebook.controllers

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono


@RunWith(SpringJUnit4ClassRunner::class)
@WebFluxTest(QuestionController::class)
class QuestionControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    private val uri = "/v1/questions/"

    @Test
    fun `Given get all end point return all`() {
        webTestClient.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(QuestionDTO::class.java)
    }

    @Test
    fun `Given a new question save it`() {
        val question = QuestionDTO( id = 1,
            identifier = "my identifier",
            content = "question content"
        )

        webTestClient.post()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(QuestionDTO::class.java)
    }

    @Test
    fun `Given a question id find it`() {
        webTestClient.get()
            .uri(uri + "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.identifier")
            .isEqualTo("my identifier")
    }

    @Test
    fun `Given an existent question update it`() {
        val question = QuestionDTO( id = 1,
            identifier = "my identifier updated",
            content = "question content"
        )

        webTestClient.put()
            .uri(uri + question.id)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.identifier")
            .isEqualTo(question.identifier)
    }

    @Test
    fun `Given an existent question delete it`() {
        webTestClient.delete()
            .uri(uri + "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
    }

}