package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.dto.FavouriteSaveDTO
import com.example.diplomnabackend.dto.ListingListDTO
import com.example.diplomnabackend.repository.FavouriteRepository
import com.example.diplomnabackend.mapper.FavouriteMapper.Companion.FAVOURITEMAPPER
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.service.FavouriteService
import org.springframework.security.core.context.SecurityContextHolder
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

    override fun findByUser(): List<ListingListDTO> {

        val user = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        val favourites = favouriteRepository.findAllByUserId(user?.getId()!!).map { FAVOURITEMAPPER.toDto(it) }

        return favourites.map { ListingListDTO(listingIds = listOf(it.listingId)) }

    }

    override fun save(favouriteDTO: FavouriteDTO): FavouriteDTO {

        val favouriteEntity = FAVOURITEMAPPER.toEntity(favouriteDTO)
        favouriteEntity.setUser(userRepository.findById(favouriteDTO.userId).orElseThrow { NoSuchElementException("User not found") })
        favouriteEntity.setListing(listingRepository.findById(favouriteDTO.listingId).orElseThrow { NoSuchElementException("Listing not found") })
        val savedFavourite = favouriteRepository.save(favouriteEntity)

        return FAVOURITEMAPPER.toDto(savedFavourite)

    }

    override fun saveByName(listingId: Long): FavouriteDTO {

        val user = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)

        val dto = FavouriteSaveDTO(listingId = listingId, userId = user!!.getId())

        val favouriteEntity = FAVOURITEMAPPER.nameToEntity(dto)
        favouriteEntity.setUser(user)
        favouriteEntity.setListing(listingRepository.findById(listingId).orElseThrow { NoSuchElementException("Listing not found") })
        val savedFavourite = favouriteEntity.let { favouriteRepository.save(it) }

        return FAVOURITEMAPPER.toDto(savedFavourite)

    }

    override fun check(listingId: Long): Boolean {

        val user = userRepository.findByEmail((SecurityContextHolder.getContext().authentication.name))

        val exists = user?.let { favouriteRepository.existsByUserIdAndListingId(it.getId(), listingId) }?: false

        return exists

    }

    override fun deleteById(id: Long) {

        favouriteRepository.deleteById(id)

    }

    override fun deleteByName(listingId: Long) {

        val userId = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)?.getId()

        userId?.let { favouriteRepository.deleteByUserIdAndListingId(it, listingId) }
    }

}