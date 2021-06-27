package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.QuestionEntity
import com.norus.apibluebook.entities.TemplateChallengeEntity
import com.norus.apibluebook.entities.TemplateChallengeQuestionEntity
import java.time.LocalDateTime

data class TemplateChallengeQuestionDTO(
    val id: Long? = null,
    val templateChallengeId: Long,
    val questionId: Long,
    val position: Int,
) {

    fun convertToTemplateChallengeQuestion() = TemplateChallengeQuestionEntity(templateChallengeId, questionId, position, LocalDateTime.now())

}