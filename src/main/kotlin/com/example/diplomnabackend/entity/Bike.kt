package com.example.diplomnabackend.entity

import com.example.diplomnabackend.entity.enums.BikeBrand
import com.example.diplomnabackend.entity.enums.BikeType
import jakarta.persistence.*

@Entity
class Bike(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @Enumerated(EnumType.STRING)
    private var type: BikeType = BikeType.MOUNTAIN,

    @Enumerated(EnumType.STRING)
    private var brand: BikeBrand,

    private var model: String,
    private var size: String,
    private var wheelSize: Int,
    private var frameMaterial: String

) {

    fun getId(): Long {
        return id
    }

    fun getType(): BikeType {
        return type
    }

    fun setType(type: BikeType) {
        this.type = type
    }

    fun getBrand(): BikeBrand {
        return brand
    }

    fun setBrand(brand: BikeBrand) {
        this.brand = brand
    }

    fun getModel(): String {
        return model
    }

    fun setModel(model: String) {
        this.model = model
    }

    fun getSize(): String {
        return size
    }

    fun setSize(size: String) {
        this.size = size
    }

    fun getWheelSize(): Int {
        return wheelSize
    }

    fun setWheelSize(wheelSize: Int) {
        this.wheelSize = wheelSize
    }

    fun getFrameMaterial(): String {
        return frameMaterial
    }

    fun setFrameMaterial(frameMaterial: String) {
        this.frameMaterial = frameMaterial
    }

}