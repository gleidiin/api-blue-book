package com.norus.apibluebook.controllers

import com.ninjasquad.springmockk.MockkBean
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import io.mockk.every
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.util.*

const val PATH = "/v1/template-challenge/"

@RunWith(SpringRunner::class)
@WebFluxTest(controllers = [TemplateChallengeController::class])
class TemplateChallengeControllerTest {

    @Autowired
    lateinit var client: WebTestClient;

    @MockkBean
    private lateinit var service: TemplateChallengeService

    @Test
    fun `should create a template challenge`() {
        val template = TemplateChallengeDTO(name = UUID.randomUUID().toString(), description = UUID.randomUUID().toString())
        every { service.createTemplateChallenge(template) } returns Mono.just(template)

        client.post()
            .uri(PATH)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(template), TemplateChallengeDTO::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectBody(TemplateChallengeDTO::class.java)
    }

    @Test
    fun `given a template challenge id find it`() {
        val monoTemplate = Mono.just(
            TemplateChallengeDTO(
                id = 1,
                name = UUID.randomUUID().toString(),
                description = UUID.randomUUID().toString()
            )
        )
        every { service.findTemplateById(1) } returns monoTemplate

        client.get()
            .uri(PATH + monoTemplate.block()!!.id)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.name")
            .isEqualTo(monoTemplate.block()!!.name)
    }

    @Test
    fun `given an existent template challenge then update it`() {
        val template = TemplateChallengeDTO(id = 1, name = UUID.randomUUID().toString(), description = UUID.randomUUID().toString())
        every { service.updateTemplateChallenge(1, template) } returns Mono.just(template)

        client.put()
            .uri(PATH + template.id)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(template), TemplateChallengeDTO::class.java)
            .exchange()
            .expectStatus().isOk
            .expectBody().jsonPath("$.description")
            .isEqualTo(template.description)
    }

    @Test
    fun `given an existent template challenge delete it`() {
        val templateId = 1L
        every { service.deleteTemplateChallenge(1) } returns Mono.empty()

        client.delete()
            .uri(PATH + templateId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }


}