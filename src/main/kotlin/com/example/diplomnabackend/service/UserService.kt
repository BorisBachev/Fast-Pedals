package com.example.diplomnabackend.service

import com.example.diplomnabackend.dto.ContactsDTO
import com.example.diplomnabackend.dto.UserDTO

interface UserService {

    fun getUserContacts(id: Long): ContactsDTO

    fun findAll(): List<UserDTO>
    fun findById(id: Long): UserDTO

    fun findByEmail(email: String): UserDTO

    fun save(userDTO: UserDTO): UserDTO
    fun update(id: Long, updatedUserDTO: UserDTO): UserDTO
    fun deleteById(id: Long)

}