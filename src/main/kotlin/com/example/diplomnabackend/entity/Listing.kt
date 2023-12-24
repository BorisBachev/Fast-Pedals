package com.example.diplomnabackend.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
class Listing (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    val user: User,

    @OneToOne
    var bike: Bike,

    var title: String,
    var description: String,
    var price: Double,
    var location: String,
    val date: String,

    @ElementCollection
    var images: List<String> = mutableListOf()

)