package com.norus.apibluebook.repository

import com.norus.apibluebook.config.ContainerConfig
import com.norus.apibluebook.config.HikariContainer
import com.norus.apibluebook.repositories.AnswerRepository
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [ContainerConfig.Initializer::class], classes = [HikariContainer::class])
class AnswerRepositoryTest {

    @Autowired
    lateinit var answerRepository: AnswerRepository


    @Test
    fun `should find all answer`(){
        Assert.assertEquals(0,answerRepository.findAll().size);
    }
}