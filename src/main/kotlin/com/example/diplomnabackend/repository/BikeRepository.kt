package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Bike
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository

interface BikeRepository : JpaRepository<Bike, Long> {

    fun findAll(spec: Specification<Bike>): List<Bike>

}