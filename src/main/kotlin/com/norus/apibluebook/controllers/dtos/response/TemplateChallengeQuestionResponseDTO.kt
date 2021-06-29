package com.norus.apibluebook.controllers.dtos.response

import com.norus.apibluebook.controllers.dtos.QuestionDTO
import com.norus.apibluebook.controllers.dtos.TemplateChallengeDTO
import com.norus.apibluebook.entities.QuestionEntity
import com.norus.apibluebook.entities.TemplateChallengeEntity
import com.norus.apibluebook.entities.TemplateChallengeQuestionEntity
import reactor.core.publisher.Mono

data class TemplateChallengeQuestionResponseDTO(
    val id: Long? = null,
    val templateChallenge: TemplateChallengeDTO,
    val question: QuestionDTO,
    val position: Int,
) {

    companion object{
        fun fromTemplateChallengeQuestion(templateChallengeQuestionEntity: TemplateChallengeQuestionEntity, templateChallengeEntity: TemplateChallengeEntity, questionEntity: QuestionEntity): TemplateChallengeQuestionResponseDTO {
            return TemplateChallengeQuestionResponseDTO(
                id = templateChallengeQuestionEntity.id,
                templateChallenge = TemplateChallengeDTO.fromTemplateChallenge(templateChallengeEntity),
                question = QuestionDTO.fromQuestionEntity(questionEntity),
                position = templateChallengeQuestionEntity.position
            )
        }

    }
}