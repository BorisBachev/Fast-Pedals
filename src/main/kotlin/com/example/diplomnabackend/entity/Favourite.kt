package com.example.diplomnabackend.entity

import jakarta.persistence.*

@Entity
class Favourite (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @ManyToOne
    private var user: User?,

    @ManyToOne
    private var listing: Listing?

) {

    fun getId(): Long {
        return id
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getListing(): Listing? {
        return listing
    }

    fun setListing(listing: Listing?) {
        this.listing = listing
    }
}