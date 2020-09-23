package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.TemplateChallengeEntity
import java.time.LocalDateTime

data class TemplateChallengeDTO(val id: Long? = null,
                                val name: String,
                                val description: String) {

    fun convertToTemplateChallage() = TemplateChallengeEntity(this.name, this.description, LocalDateTime.now(),LocalDateTime.now())

    companion object{
        fun fromTemplateChallenge(entity: TemplateChallengeEntity): TemplateChallengeDTO {
            return TemplateChallengeDTO(entity.id, entity.name, entity.description)
        }
    }

}