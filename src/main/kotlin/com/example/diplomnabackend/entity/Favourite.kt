package com.example.diplomnabackend.entity

import jakarta.persistence.*

@Entity
data class Favourite (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @ManyToOne
    private var user: User,

    @ManyToOne
    private var listing: Listing

) {

    public fun getId(): Long {
        return id
    }

    public fun getUser(): User {
        return user
    }

    public fun setUser(user: User) {
        this.user = user
    }

    public fun getListing(): Listing {
        return listing
    }

    public fun setListing(listing: Listing) {
        this.listing = listing
    }
}