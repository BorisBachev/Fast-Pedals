package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.BikeDTO

interface BikeService {

    fun findAll(): List<BikeDTO>
    fun findById(id: Long): BikeDTO?
    fun save(bikeDTO: BikeDTO): BikeDTO
    fun update(id: Long, updatedBikeDTO: BikeDTO): BikeDTO
    fun deleteById(id: Long)

}