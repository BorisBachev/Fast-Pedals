package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Favourite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface FavouriteRepository : JpaRepository<Favourite, Long> {

    fun existsByUserIdAndListingId(userId: Long, listingId: Long): Boolean

    @Transactional
    fun deleteByUserIdAndListingId(userId: Long, listingId: Long): Int

    fun findAllByUserId(userId: Long): List<Favourite>

}