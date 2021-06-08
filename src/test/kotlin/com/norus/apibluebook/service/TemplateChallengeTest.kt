package com.norus.apibluebook.service


import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.services.TemplateChallengeService
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired

@ExtendWith(MockKExtension::class)
class TemplateChallengeTest {

    @Autowired
    lateinit var templateChallengeService: TemplateChallengeService


    @Test
    fun `Given a template challenge dto create and find it`() {
        val dto = buildTemplateChallengeDTO()
        val templateChallenge = templateChallengeService.createTemplateChallenge(dto)
        val foundTemplate = templateChallengeService.findTemplateById(templateChallenge.block()!!.id!!)
        Assertions.assertNotNull(foundTemplate.block()?.name)
        Assertions.assertNotNull(foundTemplate.block()?.description)
    }

    @Test
    fun `Given a template id delete it`() {
        val dto = buildTemplateChallengeDTO()
        val templateChallenge = templateChallengeService.createTemplateChallenge(dto)
        Assertions.assertNotNull(templateChallengeService.findTemplateById(templateChallenge.block()?.id!!))
        templateChallengeService.deleteTemplateChallenge(templateChallenge.block()?.id!!)
        val error = Assertions.assertThrows(AppException::class.java, { templateChallengeService.findTemplateById(templateChallenge.block()!!.id!!) })
        Assertions.assertEquals(AppError.TEMPLATE_CHALLENGE_NOT_FOUND, error.appError)
    }

    @Test
    fun `Given a template id find and update it`() {
        var name = "test name"
        var description = "test description"
        var dto = buildTemplateChallengeDTO(name, description)
        val templateChallenge = templateChallengeService.createTemplateChallenge(dto)
        var foundTemplate = templateChallengeService.findTemplateById(templateChallenge.block()?.id!!)
        Assertions.assertEquals(name, foundTemplate.block()?.name)
        Assertions.assertEquals(description, foundTemplate.block()?.description)

        name = "other name"
        description = "other description"
        dto = buildTemplateChallengeDTO(name, description)
        //foundTemplate = templateChallengeService.updateTemplateChallenge(foundTemplate.block()?.id!!, dto)
        Assertions.assertEquals(name, foundTemplate.block()!!.name)
        Assertions.assertEquals(description, foundTemplate.block()!!.description)
    }


    private fun buildTemplateChallengeDTO(name: String = "name", description: String = "description") = TemplateChallengeDTO(name = name, description = description)

}