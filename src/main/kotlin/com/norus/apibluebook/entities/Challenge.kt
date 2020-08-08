package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "challenge")
@Table(name = "challenge")
class Challenge(
        @Column(name = "id_template_challenge") @OneToOne var templateChallenge: TemplateChallenge,
        @Column(name = "id_question") @OneToOne var question: Question,
        var started: Boolean,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Column(name = "finished_at") var finishedAt: Date,
        @Id @GeneratedValue var id: Long? = null)