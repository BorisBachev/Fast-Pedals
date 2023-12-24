package com.example.diplomnabackend.entity

import com.example.diplomnabackend.entity.enums.BikeType
import jakarta.persistence.*
import lombok.Data

@Entity
@Data
class Bike(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Enumerated(EnumType.STRING)
    var type: BikeType,
    var brand: String,
    var model: String,
    var size: String,
    var wheelSize: Int,
    var frameMaterial: String

)