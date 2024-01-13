package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Favourite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FavouriteRepository : JpaRepository<Favourite, Long>