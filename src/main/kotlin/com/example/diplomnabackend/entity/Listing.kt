package com.example.diplomnabackend.entity

import jakarta.persistence.*

@Entity
class Listing (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @ManyToOne
    private var user: User?,

    @OneToOne
    private var bike: Bike?,

    private var title: String,
    private var description: String,
    private var price: Double,
    private var location: String,
    private var date: String? = null,

    @ElementCollection
    private var images: List<String> = mutableListOf()

) {

    fun addImage(image: String) {
        images += image
    }

    fun removeImage(image: String) {
        images -= image
    }

    fun getImages(): List<String> {
        return images
    }

    fun setImages(images: List<String>) {
        this.images = images
    }

    fun getId(): Long {
        return id
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getBike(): Bike? {
        return bike
    }

    fun setBike(bike: Bike?) {
        this.bike = bike
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getLocation(): String {
        return location
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

}