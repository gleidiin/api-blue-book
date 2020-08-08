package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "answer")
@Table(name = "answer")
class Answer(
        @Column(name = "id_question") @OneToOne var question: Question,
        var content: String,
        var correct: Boolean,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Id @GeneratedValue var id: Long? = null)
