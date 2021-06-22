package com.norus.apibluebook.controllers

import com.ninjasquad.springmockk.MockkBean
import com.norus.apibluebook.controllers.dtos.AnswerDTO
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.AnswerService
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
import java.lang.StringBuilder


val uri = "/v1/questions/"

@RunWith(SpringRunner::class)
@WebFluxTest(QuestionController::class)
class QuestionControllerTest {

    @Autowired
    lateinit var client: WebTestClient;

    @MockkBean
    private lateinit var questionService: QuestionService

    @MockkBean
    private lateinit var answerService: AnswerService

    @Test
    fun `Given get all end point return all`() {
        every {
            questionService.findAllQuestion()
        } returns Flux.just(QuestionDTO(1L, "bla", "question"))

        client.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(QuestionDTO::class.java)
    }

    @Test
    fun `Should create a new question`() {
        val question = QuestionDTO(1L, "my identifier", "question content")
        every { questionService.saveQuestion(question) } returns Mono.just(question)

        client.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectBody(QuestionDTO::class.java)
    }

    @Test
    fun `Given a question id find it`() {
        val question = QuestionDTO(1L, "my identifier", "question content")
        every { questionService.findQuestionById(1L) } returns Mono.just(question)

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
        val question = QuestionDTO(1L, "my identifier", "question content")
        every { questionService.updateQuestion(1L, question) } returns Mono.just(question)

        client.put()
            .uri(uri + question.id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(question), question::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.identifier")
            .isEqualTo(question.identifier)
    }

    @Test
    fun `Should delete an existent question`() {
        every { questionService.deleteQuestion(1L) } returns Mono.empty()
        client.delete()
            .uri(uri + "1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `Should create a new answer`() {
        val question = QuestionDTO(1L, "my identifier", "question content")
        val answer = AnswerDTO(1L, question.id, "My answer content", false)

        every { questionService.findQuestionById(1L) } returns Mono.just(question)
        every { answerService.saveAnswer(answer, question) } returns Mono.just(answer)

        client.post()
            .uri(uri + "1/answers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(answer), answer::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody(AnswerDTO::class.java)
    }

    @Test
    fun `Given a answer id update it`() {
        val question = QuestionDTO(1L, "my identifier", "question content")
        val answer = AnswerDTO(1L, question.id, "My answer content updated", false)

        every { questionService.findQuestionById(1L) } returns Mono.just(question)
        every { answerService.updateAnswer(1L, answer) } returns Mono.just(answer)

        client.put()
            .uri(uri + "1/answers/1/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(answer), answer::class.java)
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.content")
            .isEqualTo(answer.content)
    }

    @Test
    fun `Should delete an existent answer`() {

        val question = QuestionDTO(1L, "my identifier", "question content")
        val answer = AnswerDTO(1L, question.id, "My answer content updated", true)

        every { questionService.findQuestionById(1L) } returns Mono.just(question)
        every { answerService.deleteAnswer(1L) } returns Mono.empty()

        client.delete()
            .uri(uri + "1/answers/1/")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()

    }

}