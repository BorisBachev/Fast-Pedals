package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.BikeDTO
import com.example.diplomnabackend.entity.Bike
import com.example.diplomnabackend.service.BikeService
import org.springframework.stereotype.Service

import com.example.diplomnabackend.mapper.BikeMapper.Companion.BIKEMAPPER
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.BikeRepository

@Service
class BikeServiceImpl(
    private val bikeRepository: BikeRepository,
    private val listingRepository: ListingRepository
) : BikeService {


    override fun findAll(): List<BikeDTO> {
        val bikes: List<Bike> = bikeRepository.findAll()
        return bikes.map { BIKEMAPPER.toDto(it) }
    }

    override fun findById(id: Long): BikeDTO? {
        val bike: Bike? = bikeRepository.findById(id).orElse(null)
        return bike?.let { BIKEMAPPER.toDto(it) }
    }

    override fun save(bikeDTO: BikeDTO): BikeDTO {
        val bikeEntity: Bike = BIKEMAPPER.toEntity(bikeDTO)
        val savedBike: Bike = bikeRepository.save(bikeEntity)
        return BIKEMAPPER.toDto(savedBike)
    }

    override fun update(id: Long, updatedBikeDTO: BikeDTO): BikeDTO {
        val existingBike: Bike = bikeRepository.findById(id).orElseThrow { NoSuchElementException("Bike not found") }

        existingBike.apply {
            type = updatedBikeDTO.type
            brand = updatedBikeDTO.brand
            model = updatedBikeDTO.model
            size = updatedBikeDTO.size
            wheelSize = updatedBikeDTO.wheelSize
            frameMaterial = updatedBikeDTO.frameMaterial
        }

        val updatedBike: Bike = bikeRepository.save(existingBike)
        return BIKEMAPPER.toDto(updatedBike)
    }

    override fun deleteById(id: Long) {
        bikeRepository.deleteById(id)
    }
}