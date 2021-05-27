package com.norus.apibluebook.service

import com.norus.apibluebook.controllers.dtos.AnswerDTO
import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.services.QuestionService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class QuestionTest {

    @Autowired
    lateinit var questionService: QuestionService

    private fun buildDTO(identifier: String = "name", content: String = "content", answers: List<AnswerDTO> = listOf(AnswerDTO(content = "test", correct = true))) = QuestionDTO(identifier = identifier, content = content, answers = answers)


    @Test
    fun `Given a question dto create and find it`() {

    }

    @Test
    fun `Given a question id delete it`() {

    }

    @Test
    fun `Given a question id find and update it`() {

    }

}