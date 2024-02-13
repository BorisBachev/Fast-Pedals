package com.example.diplomnabackend.mapper

import com.example.diplomnabackend.dto.UserDTO
import com.example.diplomnabackend.entity.User
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper {

    companion object {
        val USERMAPPER: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }

    fun toDto(entity: User): UserDTO

    fun toEntity(dto: UserDTO): User

}