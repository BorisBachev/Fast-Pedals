package com.example.diplomnabackend.mapper

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.entity.Listing
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper(uses = [UserMapper::class, BikeMapper::class])
interface ListingMapper {

    companion object {
        val LISTINGMAPPER: ListingMapper = Mappers.getMapper(ListingMapper::class.java)
    }


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "bike.id", target = "bikeId")
    fun toDto(entity: Listing): ListingDTO

    fun toEntity(dto: ListingDTO): Listing

}