package com.example.diplomnabackend.config

import com.example.diplomnabackend.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
@RequiredArgsConstructor
class ApplicationConfig (

    private val userRepository: UserRepository

) {

    @Bean
    fun userDetailService() : UserDetailsService {
       // return username -> userRepository.findByEmail(username).orElseThrow { UsernameNotFoundException("User not found") }
        return null as UserDetailsService
    }

}