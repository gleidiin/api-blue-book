package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "challenge_answer")
@Table(name = "challenge_answer")
class ChallengeAnswer (
        @Column(name = "id_challenge") @OneToOne(fetch = FetchType.LAZY) var challenge: Challenge,
        @Column(name = "id_question") @OneToOne(fetch = FetchType.LAZY) var question: Question,
        var nickname: String,
        var right: Boolean,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Id @GeneratedValue var id: Long? = null)