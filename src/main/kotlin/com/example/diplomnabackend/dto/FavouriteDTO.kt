package com.example.diplomnabackend.dto

import lombok.Data

@Data
data class FavouriteDTO(

    val id: Long,
    val userId: Long,
    val listingId: Long

)