package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Favourite
import org.springframework.data.jpa.repository.JpaRepository

interface FavouriteRepository : JpaRepository<Favourite, Long>