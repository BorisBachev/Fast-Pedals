package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.UserDTO

interface UserService {

    fun findAll(): List<UserDTO>
    fun findById(id: Long): UserDTO
    fun save(userDTO: UserDTO): UserDTO
    fun update(id: Long, updatedUserDTO: UserDTO): UserDTO
    fun deleteById(id: Long)

}