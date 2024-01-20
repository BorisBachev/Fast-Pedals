package com.example.diplomnabackend.config

import com.example.diplomnabackend.entity.User
import com.example.diplomnabackend.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig (

    private val userRepository: UserRepository

) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return object : UserDetailsService {
            override fun loadUserByUsername(username: String): UserDetails {
                val user = userRepository.findByEmail(username)
                    ?: throw UsernameNotFoundException("User not found")

                return user.toUserDetails()
            }
        }
    }

    private fun User.toUserDetails(): UserDetails {

        val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_${getRole().name}"))

        return org.springframework.security.core.userdetails.User(
            getEmail(),
            password,
            authorities
        )
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(userDetailsService())
        return provider
    }

    @Bean
    fun publicAuthenticationManager(config : AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}