package com.example.diplomnabackend.entity

import com.example.diplomnabackend.entity.enums.BikeBrand
import com.example.diplomnabackend.entity.enums.BikeType
import jakarta.persistence.*

@Entity
data class Bike(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Enumerated(EnumType.STRING)
    private var type: BikeType,

    @Enumerated(EnumType.STRING)
    private var brand: BikeBrand,

    private var model: String,
    private var size: String,
    private var wheelSize: Int,
    private var frameMaterial: String

) {

    public fun getId(): Long {
        return id
    }

    public fun getType(): BikeType {
        return type
    }

    public fun setType(type: BikeType) {
        this.type = type
    }

    public fun getBrand(): BikeBrand {
        return brand
    }

    public fun setBrand(brand: BikeBrand) {
        this.brand = brand
    }

    public fun getModel(): String {
        return model
    }

    public fun setModel(model: String) {
        this.model = model
    }

    public fun getSize(): String {
        return size
    }

    public fun setSize(size: String) {
        this.size = size
    }

    public fun getWheelSize(): Int {
        return wheelSize
    }

    public fun setWheelSize(wheelSize: Int) {
        this.wheelSize = wheelSize
    }

    public fun getFrameMaterial(): String {
        return frameMaterial
    }

    public fun setFrameMaterial(frameMaterial: String) {
        this.frameMaterial = frameMaterial
    }

}