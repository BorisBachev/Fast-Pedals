package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.ListingDTO

interface ListingService {

    fun findAll(): List<ListingDTO>
    fun findById(id: Long): ListingDTO
    fun save(listingDTO: ListingDTO): ListingDTO
    fun update(id: Long, updatedListingDTO: ListingDTO): ListingDTO
    fun deleteById(id: Long)

}