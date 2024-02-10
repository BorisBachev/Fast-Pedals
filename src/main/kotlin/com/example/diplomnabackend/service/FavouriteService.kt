package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.dto.FavouriteNameDTO

interface FavouriteService {

    fun findAll(): List<FavouriteDTO>
    fun findById(id: Long): FavouriteDTO
    fun save(favouriteDTO: FavouriteDTO): FavouriteDTO

    fun saveByName(favouriteDTO: FavouriteNameDTO): FavouriteDTO

    fun check(favouriteDTO: FavouriteNameDTO): Boolean

    fun deleteById(id: Long)

    fun deleteByName(userEmail: String, listingId: Long)

}