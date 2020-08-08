package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "template_challenge_question")
@Table(name = "template_challenge_question")
class TemplateChallengeQuestion(
        @Column(name = "id_template_challenge") @OneToOne(fetch = FetchType.LAZY) var templateChallenge: TemplateChallenge,
        @Column(name = "id_question") @OneToOne var question: Question,
        var order: Int,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Id @GeneratedValue var id: Long? = null)