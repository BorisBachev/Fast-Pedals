package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.dto.ListingNameDTO
import com.example.diplomnabackend.entity.Bike
import com.example.diplomnabackend.mapper.BikeMapper
import com.example.diplomnabackend.mapper.ListingMapper.Companion.LISTINGMAPPER
import com.example.diplomnabackend.repository.BikeRepository
import com.example.diplomnabackend.repository.FavouriteRepository
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.service.ListingService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


@Service
class ListingServiceImpl (
    private val listingRepository: ListingRepository,
    private val bikeRepository: BikeRepository,
    private val userRepository: UserRepository,
    private val favouriteRepository: FavouriteRepository
) : ListingService {

    val err = NoSuchElementException("Listing not found")

    override fun findAll(): List<ListingDTO> {
        return listingRepository.findAll().map { LISTINGMAPPER.toDto(it) }
    }

    override fun findById(id: Long): ListingDTO {
        val listing = listingRepository.findById(id).orElseThrow { err }
        return LISTINGMAPPER.toDto(listing)
    }

    override fun getFavouriteListings(): List<ListingDTO>? {

        val user = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        val favourites = favouriteRepository.findAllByUserId(user?.getId()!!).map { it.getListing()?.getId() }

        val listings = mutableListOf<ListingDTO>()

        favourites.forEach { favoriteId ->
            favoriteId?.let { id ->
                listingRepository.findById(id)?.ifPresent { listing ->
                    listings.add(LISTINGMAPPER.toDto(listing))
                }
            }
        }

        return if (listings.isNotEmpty()) listings else null

    }

    override fun save(listingDTO: ListingDTO): ListingDTO {

        val listingEntity = LISTINGMAPPER.toEntity(listingDTO)
        bikeRepository.findById(listingDTO.bikeId).ifPresent { listingEntity.setBike(it) }
        listingEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name))
        val savedListing = listingRepository.save(listingEntity)
        return LISTINGMAPPER.toDto(savedListing)

    }

    override fun saveByUser(listingDTO: ListingNameDTO): ListingDTO {

        val listingEntity = LISTINGMAPPER.nameToEntity(listingDTO)
        val bikeDTO = BikeMapper.BIKEMAPPER.nameToDto(listingDTO)
        val bikeEntity: Bike = BikeMapper.BIKEMAPPER.toEntity(bikeDTO)
        val savedBike: Bike = bikeRepository.save(bikeEntity)

        listingEntity.setBike(savedBike)
        listingEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name))

        val savedListing = listingRepository.save(listingEntity)

        return LISTINGMAPPER.toDto(savedListing)

    }

    override fun update(id: Long, updatedListingDTO: ListingDTO): ListingDTO {

        val existingListing = listingRepository.findById(id).orElseThrow { err }

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

    override fun updateByUser(updatedListingDTO: ListingNameDTO): ListingDTO {

        val existingListing = listingRepository.findById(updatedListingDTO.id).orElseThrow { err }

        val existingBike: Bike = bikeRepository.findById(updatedListingDTO.bikeId!!).orElseThrow { NoSuchElementException("Bike not found") }

        existingBike.apply {
            setType(updatedListingDTO.type)
            setBrand(updatedListingDTO.brand)
            setModel(updatedListingDTO.model)
            setSize(updatedListingDTO.size)
            setWheelSize(updatedListingDTO.wheelSize)
            setFrameMaterial(updatedListingDTO.frameMaterial)
        }

        val updatedBike: Bike = bikeRepository.save(existingBike)

        existingListing.apply {
            setTitle(updatedListingDTO.title)
            setDescription(updatedListingDTO.description)
            setPrice(updatedListingDTO.price)
            setLocation(updatedListingDTO.location)
            setImages(updatedListingDTO.images)
            setBike(updatedBike)
        }

        val updatedListing = listingRepository.save(existingListing)
        return LISTINGMAPPER.toDto(updatedListing)

    }

    override fun deleteById(id: Long) {

        listingRepository.deleteById(id)

    }

    override fun searchListings(
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
    ): List<ListingDTO?>? {

        val listings =listingRepository.searchListings(
            title, minPrice, maxPrice, location, description, type, brand, model, size, wheelSize, frameMaterial, userId
        )?: throw err

        return listings.map { LISTINGMAPPER.toDto(it!!) }
    }

}