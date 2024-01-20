package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.repository.FavouriteRepository
import com.example.diplomnabackend.mapper.FavouriteMapper.Companion.FAVOURITEMAPPER
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.service.FavouriteService
import org.springframework.stereotype.Service

@Service
class FavouriteServiceImpl (
    private val favouriteRepository: FavouriteRepository,
    private val listingRepository: ListingRepository,
    private val userRepository: UserRepository
    ) : FavouriteService {

    override fun findAll(): List<FavouriteDTO> {
        return favouriteRepository.findAll().map { FAVOURITEMAPPER.toDto(it) }
    }

    override fun findById(id: Long): FavouriteDTO {

        val favourite = favouriteRepository.findById(id).orElseThrow { NoSuchElementException("Favourite not found") }
        return FAVOURITEMAPPER.toDto(favourite)

    }

    override fun save(favouriteDTO: FavouriteDTO): FavouriteDTO {

        val favouriteEntity = FAVOURITEMAPPER.toEntity(favouriteDTO)
        favouriteEntity.setUser(userRepository.findById(favouriteDTO.userId).orElseThrow { NoSuchElementException("User not found") })
        favouriteEntity.setListing(listingRepository.findById(favouriteDTO.listingId).orElseThrow { NoSuchElementException("Listing not found") })
        val savedFavourite = favouriteRepository.save(favouriteEntity)
        return FAVOURITEMAPPER.toDto(savedFavourite)

    }

    override fun deleteById(id: Long) {

        favouriteRepository.deleteById(id)

    }

}