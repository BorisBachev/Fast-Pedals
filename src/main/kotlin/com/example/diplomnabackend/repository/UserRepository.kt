package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {



}