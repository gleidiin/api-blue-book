package com.norus.apibluebook.controllers

import com.ninjasquad.springmockk.MockkBean
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.QuestionService
import io.mockk.every
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RunWith(SpringRunner::class)
@WebFluxTest(QuestionController::class)
class QuestionControllerTest {

    @Autowired
    lateinit var client: WebTestClient;

    @MockkBean
    private lateinit var service: QuestionService
    private val uri = "/v1/questions/"

    @Test
    fun `Given get all end point return all`() {
        every {
            service.findAllQuestion()
        } returns Flux.just(QuestionDTO(0, "bla", "question"))

        client.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(QuestionDTO::class.java)
    }

    @Test
    fun `Given a new question save it`() {
        val question = QuestionDTO(1, "my identifier", "question content")
        every { service.saveQuestion(question) } returns Mono.just(question)

        client.post()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectBody(QuestionDTO::class.java)
    }

    @Test
    fun `Given a question id find it`() {
        val question = QuestionDTO(1, "my identifier", "question content")
        every { service.findQuestionById(1) } returns Mono.just(question)

        client.get()
            .uri(uri + question.id)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.identifier")
            .isEqualTo("my identifier")
    }

    @Test
    fun `Given an existent question update it`() {
        val question = QuestionDTO( id = 1,
            identifier = "my identifier updated",
            content = "question content"
        )

        client.put()
            .uri(uri + question.id)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.identifier")
            .isEqualTo(question.identifier)
    }

    @Test
    fun `Given an existent question delete it`() {
        client.delete()
            .uri(uri + "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }

}