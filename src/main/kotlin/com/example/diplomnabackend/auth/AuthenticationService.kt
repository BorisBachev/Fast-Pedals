package com.example.diplomnabackend.auth

import com.example.diplomnabackend.config.JwtService
import com.example.diplomnabackend.entity.User
import com.example.diplomnabackend.repository.UserRepository
import com.example.diplomnabackend.entity.enums.Role
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService (

    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager

) {

    fun register(request: RegisterRequest): AuthenticationResponse {

        val user = User.build(request.email, passwordEncoder.encode(request.password), Role.USER, request.name, request.fullName, request.phoneNumber, request.profilePicture)

        userRepository.save(user)

        val token = jwtService.generateToken(user)

        return AuthenticationResponse.build(token)

    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val user = userRepository.findByEmail(request.email)

        val token = jwtService.generateToken(user!!)

        return AuthenticationResponse.build(token)

    }

    fun check(): CheckResponse {

        val user = userRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)

        val token = jwtService.generateToken(user!!)

        return CheckResponse(token)

    }

}