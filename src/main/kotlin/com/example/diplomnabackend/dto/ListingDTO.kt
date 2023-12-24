package com.example.diplomnabackend.dto

import lombok.Data

@Data
class ListingDTO (

    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val location: String,
    val date: String? = null,
    val images: List<String>,
    val bikeId: Long,
    val userId: Long

)