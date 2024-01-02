package com.example.diplomnabackend.dto

import com.example.diplomnabackend.entity.enums.BikeBrand
import com.example.diplomnabackend.entity.enums.BikeType
import lombok.Data

@Data
class BikeDTO(
    val id: Long,
    val type: BikeType,
    val brand: BikeBrand,
    val model: String,
    val size: String,
    val wheelSize: Int,
    val frameMaterial: String
)
