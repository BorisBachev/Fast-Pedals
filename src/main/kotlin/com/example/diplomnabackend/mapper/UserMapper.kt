package com.example.diplomnabackend.mapper

import com.example.diplomnabackend.dto.UserDTO
import com.example.diplomnabackend.entity.User
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper//(uses = [FavouriteMapper::class, ListingMapper::class])
interface UserMapper {

    companion object {
        val USERMAPPER: UserMapper = Mappers.getMapper(UserMapper::class.java)
        //val LISTINGMAPPER: ListingMapper = Mappers.getMapper(ListingMapper::class.java)
        //val FAVOURITEMAPPER: FavouriteMapper = Mappers.getMapper(FavouriteMapper::class.java)
    }

    fun toDto(entity: User): UserDTO
    fun toEntity(dto: UserDTO): User
/*
    fun toDtoListingsNullable(entity: User): UserDTO {
        val dto = toDto(entity)
        dto.listings = entity.listings?.map { LISTINGMAPPER.toDto(it) }?.toList()
        dto.favourites = entity.favourites?.map { FAVOURITEMAPPER.toDto(it) }?.toList()
        return dto
    }

    fun toEntityListingsNullable(dto: UserDTO): User {
        val entity = toEntity(dto)
        entity.listings = dto.listings?.map { LISTINGMAPPER.toEntity(it) }?.toList()!!
        entity.favourites = dto.favourites?.map { FAVOURITEMAPPER.toEntity(it) }?.toList()!!
        return entity
    }*/
}