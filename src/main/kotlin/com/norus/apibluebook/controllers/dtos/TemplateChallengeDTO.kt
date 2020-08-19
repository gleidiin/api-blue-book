package com.norus.apibluebook.controllers.dtos

import com.norus.apibluebook.entities.TemplateChallengeEntity
import java.time.LocalDateTime

data class TemplateChallengeDTO(val id: Long? = null,
                                val name: String,
                                val description: String) {

    fun convertToTemplateChallageDTO() = TemplateChallengeEntity(this.name, this.description, LocalDateTime.now())

    companion object{
        fun fromTemplateChallenge(entity: TemplateChallengeEntity): TemplateChallengeDTO {
            return TemplateChallengeDTO(entity.id, entity.name, entity.description)
        }
    }

}