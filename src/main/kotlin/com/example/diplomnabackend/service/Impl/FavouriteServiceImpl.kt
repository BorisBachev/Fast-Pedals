package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.dto.FavouriteNameDTO
import com.example.diplomnabackend.repository.FavouriteRepository
import com.example.diplomnabackend.mapper.FavouriteMapper.Companion.FAVOURITEMAPPER
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.service.FavouriteService
import lombok.extern.java.Log
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

    override fun saveByName(favouriteDTO: FavouriteNameDTO): FavouriteDTO {

        val favouriteEntity = FAVOURITEMAPPER.nameToEntity(favouriteDTO)
        favouriteEntity.setUser(userRepository.findByEmail(favouriteDTO.userEmail)?: throw NoSuchElementException("User not found"))
        favouriteEntity.setListing(listingRepository.findById(favouriteDTO.listingId).orElseThrow { NoSuchElementException("Listing not found") })
        val savedFavourite = favouriteRepository.save(favouriteEntity)

        return FAVOURITEMAPPER.toDto(savedFavourite)

    }

    override fun check(favouriteDTO: FavouriteNameDTO): Boolean {

        val user = userRepository.findByEmail(favouriteDTO.userEmail)

        val exists = user?.let { favouriteRepository.existsByUserIdAndListingId(it.getId(), favouriteDTO.listingId) }?: false

        return exists

    }

    override fun deleteById(id: Long) {

        favouriteRepository.deleteById(id)

    }

    override fun deleteByName(userEmail: String, listingId: Long) {

        val userId = userRepository.findByEmail(userEmail)?.getId()?: throw NoSuchElementException("User not found")

        favouriteRepository.deleteByUserIdAndListingId(userId, listingId)
    }

}