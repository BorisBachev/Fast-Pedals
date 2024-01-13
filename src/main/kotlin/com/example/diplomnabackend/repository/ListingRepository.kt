package com.example.diplomnabackend.repository

import com.example.diplomnabackend.entity.Listing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ListingRepository : JpaRepository<Listing, Long>