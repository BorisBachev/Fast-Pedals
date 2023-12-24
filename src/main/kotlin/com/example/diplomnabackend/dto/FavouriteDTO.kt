package com.example.diplomnabackend.dto

import lombok.Data

@Data
class FavouriteDTO(

    val id: Long,
    val userId: Long,
    val listingId: Long

)