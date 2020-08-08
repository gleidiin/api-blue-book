package com.norus.apibluebook.entities

import java.util.*
import javax.persistence.*

@Entity(name = "template_challenge")
@Table(name = "template_challenge")
class TemplateChallenge(
        var name: String,
        var description: String,
        @Column(name = "reference_code") var referenceCode: String,
        @Column(name = "created_at") var createdAt: Date,
        @Column(name = "updated_at") var updatedAt: Date,
        @Id @GeneratedValue var id: Long? = null)
