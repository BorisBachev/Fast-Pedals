package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Listing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface ListingRepository : JpaRepository<Listing, Long> {

    @Query(
        "SELECT l FROM Listing l " +
        "LEFT JOIN FETCH l.bike b " +
        "WHERE (:title IS NULL OR LOWER(l.title) LIKE %:title%) " +
        "AND (:minPrice IS NULL OR l.price >= :minPrice) " +
        "AND (:maxPrice IS NULL OR l.price <= :maxPrice) " +
        "AND (:location IS NULL OR LOWER(l.location) LIKE %:location%) " +
        "AND (:description IS NULL OR LOWER(l.description) LIKE %:description%) " +
        "AND (:type IS NULL OR LOWER(b.type) = LOWER(:type) OR :type = '') " +
        "AND (:brand IS NULL OR LOWER(b.brand) = LOWER(:brand) OR :brand = '') " +
        "AND (:model IS NULL OR LOWER(b.model) LIKE %:model%) " +
        "AND (:size IS NULL OR LOWER(b.size) LIKE %:size%) " +
        "AND (:wheelSize IS NULL OR b.wheelSize = :wheelSize) " +
        "AND (:frameMaterial IS NULL OR LOWER(b.frameMaterial) LIKE %:frameMaterial%)"
    )
    fun searchListings(
        @Param("title") title: String?,
        @Param("minPrice") minPrice: Double?,
        @Param("maxPrice") maxPrice: Double?,
        @Param("location") location: String?,
        @Param("description") description: String?,
        @Param("type") type: String?,
        @Param("brand") brand: String?,
        @Param("model") model: String?,
        @Param("size") size: String?,
        @Param("wheelSize") wheelSize: Int?,
        @Param("frameMaterial") frameMaterial: String?
    ): List<Listing?>?
}