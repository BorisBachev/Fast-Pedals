package com.example.diplomnabackend.mapper

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.dto.FavouriteNameDTO
import com.example.diplomnabackend.entity.Favourite
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper(uses = [UserMapper::class, ListingMapper::class])
interface FavouriteMapper {

    companion object {
        val FAVOURITEMAPPER: FavouriteMapper = Mappers.getMapper(FavouriteMapper::class.java)
    }

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "listing.id", target = "listingId")
    fun toDto(entity: Favourite): FavouriteDTO

    fun toEntity(dto: FavouriteDTO): Favourite

    fun nameToEntity(dto: FavouriteNameDTO): Favourite

}