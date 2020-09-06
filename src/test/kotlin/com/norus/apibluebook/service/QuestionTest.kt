package com.norus.apibluebook.service

import com.norus.apibluebook.config.ContainerConfig
import com.norus.apibluebook.config.HikariContainer
import com.norus.apibluebook.services.QuestionService
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

}