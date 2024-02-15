package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.dto.ListingListDTO
import com.example.diplomnabackend.dto.ListingNameDTO

interface ListingService {

    fun findAll(): List<ListingDTO>
    fun findById(id: Long): ListingDTO

    fun getFavouriteListings(): List<ListingDTO>

    fun save(listingDTO: ListingDTO): ListingDTO

    fun saveByUser(listingDTO: ListingNameDTO): ListingDTO

    fun update(id: Long, updatedListingDTO: ListingDTO): ListingDTO

    fun updateByUser(updatedListingDTO: ListingNameDTO): ListingDTO

    fun deleteById(id: Long)

    fun searchListings(
        title: String?,
        minPrice: Double?,
        maxPrice: Double?,
        location: String?,
        description: String?,
        type: String?,
        brand: String?,
        model: String?,
        size: String?,
        wheelSize: Int?,
        frameMaterial: String?,
        userId: Long?
    ): List<ListingDTO?>?

}