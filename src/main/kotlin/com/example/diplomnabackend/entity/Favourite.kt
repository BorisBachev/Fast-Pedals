package com.example.diplomnabackend.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
class Favourite (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    var user: User,

    @ManyToOne
    var listing: Listing

)