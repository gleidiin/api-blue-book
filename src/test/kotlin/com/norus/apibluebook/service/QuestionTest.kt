package com.norus.apibluebook.service

import com.norus.apibluebook.config.ContainerConfig
import com.norus.apibluebook.config.HikariContainer
import com.norus.apibluebook.configs.AppError
import com.norus.apibluebook.configs.AppException
import com.norus.apibluebook.controllers.dtos.AnswerDTO
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.QuestionService
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
class QuestionTest {

    @Autowired
    lateinit var questionService: QuestionService

    private fun buildDTO(identifier: String = "name", content: String = "content", answers: List<AnswerDTO> = listOf(AnswerDTO(content = "test", correct = true))) = QuestionDTO(identifier = identifier, content = content, answers = answers)


    @Test
    fun `Given a question dto create and find it`() {
        val dto = buildDTO()
        val savedQuestion = questionService.saveQuestion(dto)
        val findQuestionById = questionService.findQuestionById(savedQuestion.id!!)

        Assert.assertNotNull(findQuestionById.identifier)
        Assert.assertNotNull(findQuestionById.content)
        Assert.assertNotNull(findQuestionById.answers)
    }

    @Test
    fun `Given a question id delete it`() {
        val dto = buildDTO()
        val savedQuestion = questionService.saveQuestion(dto)
        Assert.assertNotNull(questionService.findQuestionById(savedQuestion.id!!))
        questionService.deleteQuestion(savedQuestion.id!!)
        val error = Assertions.assertThrows(AppException::class.java, { questionService.findQuestionById(savedQuestion.id!!) })
        Assert.assertEquals(AppError.QUESTION_NOT_FOUND, error.appError)
    }

    @Test
    fun `Given a question id find and update it`() {
        val defaultDTO = buildDTO()
        val savedQuestion = questionService.saveQuestion(defaultDTO)
        val extractedIdentifier = savedQuestion.identifier + " - modified"
        val extractedContent = savedQuestion.content + " - modified"
        val extractedAnswers = savedQuestion.answers.map { answer -> AnswerDTO(id = answer.id!!, content = answer.content + " - content", correct = true)}

        val findQuestionById = questionService.findQuestionById(savedQuestion.id!!)

        Assert.assertNotNull(findQuestionById.identifier)
        Assert.assertNotNull(findQuestionById.content)
        Assert.assertNotNull(findQuestionById.answers)

        val newDTO = buildDTO(identifier = extractedIdentifier, content = extractedContent, answers = extractedAnswers)

        val updatedQuestion = questionService.updateQuestion(findQuestionById.id!!, newDTO)
        Assert.assertEquals(updatedQuestion.identifier, extractedIdentifier)
        Assert.assertEquals(updatedQuestion.content, extractedContent)
        Assert.assertEquals(updatedQuestion.answers.size, extractedAnswers.size)
    }

}