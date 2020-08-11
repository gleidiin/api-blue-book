package com.norus.apibluebook.repositories

import com.norus.apibluebook.entities.QuestionEntity
import org.springframework.data.repository.CrudRepository


interface QuestionRepository: CrudRepository<QuestionEntity, Long>