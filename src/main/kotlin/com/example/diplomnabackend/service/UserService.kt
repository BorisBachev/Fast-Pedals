package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.UserDTO

interface UserService {

    fun findAll(): List<UserDTO>
    fun findById(id: Long): UserDTO

    fun findByEmail(): UserDTO

    fun save(userDTO: UserDTO): UserDTO
    fun update(id: Long, updatedUserDTO: UserDTO): UserDTO

    fun updateFcm(fcm: String): UserDTO

    fun logout(): UserDTO

    fun deleteById(id: Long)

}