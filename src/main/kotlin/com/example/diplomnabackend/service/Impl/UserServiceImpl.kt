package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.dto.ContactsDTO
import com.example.diplomnabackend.dto.UserDTO
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.mapper.UserMapper.Companion.USERMAPPER
import com.example.diplomnabackend.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository
) : UserService {

    val err = NoSuchElementException("User not found")

    override fun getUserContacts(id: Long): ContactsDTO {

        val user = userRepository.findById(id).orElseThrow { err }
        return USERMAPPER.toContactsDto(user)

    }

    override fun findAll(): List<UserDTO> {

        return userRepository.findAll().map { USERMAPPER.toDto(it) }

    }

    override fun findById(id: Long): UserDTO {

        val user = userRepository.findById(id).orElseThrow { err }
        return USERMAPPER.toDto(user)

    }

    override fun save(userDTO: UserDTO): UserDTO {

        val userEntity = USERMAPPER.toEntity(userDTO)
        val savedUser = userRepository.save(userEntity)
        return USERMAPPER.toDto(savedUser)

    }

    override fun update(id: Long, updatedUserDTO: UserDTO): UserDTO {

        val existingUser = userRepository.findById(id).orElseThrow { err }

        existingUser.apply {
            setName(updatedUserDTO.name)
            setPassw(updatedUserDTO.passw)
            setEmail(updatedUserDTO.email)
            setFullName(updatedUserDTO.fullName)
            setPhoneNumber(updatedUserDTO.phoneNumber)
            setProfilePicture(updatedUserDTO.profilePicture)
        }

        val updatedUser = userRepository.save(existingUser)
        return USERMAPPER.toDto(updatedUser)

    }

    override fun deleteById(id: Long) {

            userRepository.deleteById(id)

    }

}