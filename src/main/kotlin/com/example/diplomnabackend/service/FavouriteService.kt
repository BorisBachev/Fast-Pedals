package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.dto.ListingListDTO

interface FavouriteService {

    fun findAll(): List<FavouriteDTO>
    fun findById(id: Long): FavouriteDTO

    fun findByUser(): List<ListingListDTO>

    fun save(favouriteDTO: FavouriteDTO): FavouriteDTO

    fun saveByName(listingId: Long): FavouriteDTO

    fun check(listingId: Long): Boolean

    fun deleteById(id: Long)

    fun deleteByName(listingId: Long)

}