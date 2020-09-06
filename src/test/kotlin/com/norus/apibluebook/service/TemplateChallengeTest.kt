package com.norus.apibluebook.service

import com.norus.apibluebook.config.ContainerConfig
import com.norus.apibluebook.config.HikariContainer
import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [ContainerConfig.Initializer::class], classes = [HikariContainer::class])
@ActiveProfiles(profiles = arrayOf("dev"))
class TemplateChallengeTest {

    @Autowired
    lateinit var templateChallengeService: TemplateChallengeService


    @Test
    fun `Given a template challenge dto create and find it`() {
        val dto = buildTemplateChallengeDTO()
        val templateChallenge = templateChallengeService.saveTemplateChallenge(dto)
        val foundTemplate = templateChallengeService.findTemplateChallengeById(templateChallenge.id!!)
        Assert.assertNotNull(foundTemplate.name)
        Assert.assertNotNull(foundTemplate.description)
    }

    @Test
    fun `Given a template id delete it`() {
        val dto = buildTemplateChallengeDTO()
        val templateChallenge = templateChallengeService.saveTemplateChallenge(dto)
        Assert.assertNotNull(templateChallengeService.findTemplateChallengeById(templateChallenge.id!!))
        templateChallengeService.deleteTemplateChallengeById(templateChallenge.id!!)
        val error = Assertions.assertThrows(AppException::class.java, { templateChallengeService.findTemplateChallengeById(templateChallenge.id!!) })
        Assert.assertEquals(AppError.TEMPLATE_CHALLENGE_NOT_FOUND, error.appError)
    }

    @Test
    fun `Given a template id find and update it`() {
        var name = "test name"
        var description = "test description"
        var dto = buildTemplateChallengeDTO(name, description)
        val templateChallenge = templateChallengeService.saveTemplateChallenge(dto)
        var foundTemplate = templateChallengeService.findTemplateChallengeById(templateChallenge.id!!)
        Assert.assertEquals(name,foundTemplate.name)
        Assert.assertEquals(description,foundTemplate.description)

        name = "other name"
        description = "other description"
        dto = buildTemplateChallengeDTO(name, description)
        foundTemplate = templateChallengeService.updateTemplateChallenge(dto, foundTemplate.id!!)
        Assert.assertEquals(name,foundTemplate.name)
        Assert.assertEquals(description,foundTemplate.description)
    }


    private fun buildTemplateChallengeDTO(name: String = "name", description: String = "description") = TemplateChallengeDTO(name = name, description = description)

}