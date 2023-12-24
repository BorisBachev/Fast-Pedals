package com.example.diplomnabackend.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var phoneNumber: String,

    var profilePicture: String?,

    @OneToMany
    var listings: List<Listing>? = mutableListOf(),

    @OneToMany
    var favourites: List<Favourite> = mutableListOf()

)
