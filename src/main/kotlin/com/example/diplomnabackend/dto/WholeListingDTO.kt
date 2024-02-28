package com.example.diplomnabackend.dto

import com.example.diplomnabackend.entity.enums.BikeBrand
import com.example.diplomnabackend.entity.enums.BikeType

class WholeListingDTO(

    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val location: String,
    val date: String? = null,
    val images: List<String>,
    val type: BikeType,
    val brand: BikeBrand,
    val model: String,
    val size: String,
    val wheelSize: Int,
    val frameMaterial: String,
    val bikeId: Long,
    val userId: Long,
    val name: String,
    val phoneNumber: String,
    val isFavourite: Boolean,
    val isOwner: Boolean

)
