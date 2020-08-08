package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "question")
@Table(name = "question")
class Question(
        var identifier: String,
        var content: String,
        @ManyToOne(targetEntity = Answer::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) var answers: List<Answer>,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Id @GeneratedValue var id: Long? = null)
