package com.example.diplomnabackend.entity

import jakarta.persistence.*

@Entity
data class Listing (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @ManyToOne
    private val user: User,

    @OneToOne
    private var bike: Bike,

    private var title: String,
    private var description: String,
    private var price: Double,
    private var location: String,
    private var date: String,

    @ElementCollection
    private var images: List<String> = mutableListOf()

) {

    public fun addImage(image: String) {
        images += image
    }

    public fun removeImage(image: String) {
        images -= image
    }

    public fun getImages(): List<String> {
        return images
    }

    public fun setImages(images: List<String>) {
        this.images = images
    }

    public fun getId(): Long {
        return id
    }

    public fun getUser(): User {
        return user
    }

    public fun getBike(): Bike {
        return bike
    }

    public fun getTitle(): String {
        return title
    }

    public fun setTitle(title: String) {
        this.title = title
    }

    public fun getDescription(): String {
        return description
    }

    public fun setDescription(description: String) {
        this.description = description
    }

    public fun getPrice(): Double {
        return price
    }

    public fun setPrice(price: Double) {
        this.price = price
    }

    public fun getLocation(): String {
        return location
    }

    public fun setLocation(location: String) {
        this.location = location
    }

    public fun getDate(): String {
        return date
    }

    public fun setDate(date: String) {
        this.date = date
    }

    public fun setBike(bike: Bike) {
        this.bike = bike
    }

}