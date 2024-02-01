package com.example.diplomnabackend.mapper

import com.example.diplomnabackend.dto.BikeDTO
import com.example.diplomnabackend.entity.Bike
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface BikeMapper {

    companion object {
        val BIKEMAPPER: BikeMapper = Mappers.getMapper(BikeMapper::class.java)
    }

    fun toDto(entity: Bike): BikeDTO

    fun toEntity(dto: BikeDTO): Bike

}
