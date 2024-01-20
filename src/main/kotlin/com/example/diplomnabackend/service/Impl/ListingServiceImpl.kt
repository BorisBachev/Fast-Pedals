package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.entity.User
import com.example.diplomnabackend.mapper.ListingMapper.Companion.LISTINGMAPPER
import com.example.diplomnabackend.repository.BikeRepository
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.service.ListingService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class ListingServiceImpl (
    private val listingRepository: ListingRepository,
    private val bikeRepository: BikeRepository,
    private val userRepository: UserRepository
) : ListingService {

    override fun findAll(): List<ListingDTO> {
        return listingRepository.findAll().map { LISTINGMAPPER.toDto(it) }
    }

    override fun findById(id: Long): ListingDTO {
        val listing = listingRepository.findById(id).orElseThrow { NoSuchElementException("Listing not found") }
        return LISTINGMAPPER.toDto(listing)
    }

    override fun save(listingDTO: ListingDTO): ListingDTO {


        val listingEntity = LISTINGMAPPER.toEntity(listingDTO)
        bikeRepository.findById(listingDTO.bikeId).ifPresent { listingEntity.setBike(it) }
        listingEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name))
        val savedListing = listingRepository.save(listingEntity)
        return LISTINGMAPPER.toDto(savedListing)

    }

    override fun update(id: Long, updatedListingDTO: ListingDTO): ListingDTO {

        val existingListing = listingRepository.findById(id).orElseThrow { NoSuchElementException("Listing not found") }

        existingListing.apply {
            setTitle(updatedListingDTO.title)
            setDescription(updatedListingDTO.description)
            setPrice(updatedListingDTO.price)
            setLocation(updatedListingDTO.location)
            setImages(updatedListingDTO.images)
        }

        val updatedListing = listingRepository.save(existingListing)
        return LISTINGMAPPER.toDto(updatedListing)

    }

    override fun deleteById(id: Long) {

        listingRepository.deleteById(id)

    }

}