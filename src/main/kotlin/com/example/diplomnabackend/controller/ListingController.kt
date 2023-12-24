package com.example.diplomnabackend.controller

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.service.ListingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/listing")
class ListingController (
    private val listingService: ListingService
) {

    @GetMapping
    fun getAllListings() : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.findAll())
    }

    @GetMapping("/{id}")
    fun getListingById(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.findById(id))
    }

    @PostMapping
    fun saveListing(@RequestBody listingDTO: ListingDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.save(listingDTO))
    }

    @PutMapping("/{id}")
    fun updateListing(@PathVariable id: Long, @RequestBody updatedListingDTO: ListingDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.update(id, updatedListingDTO))
    }

    @PostMapping("/{id}")
    fun deleteListing(@PathVariable id: Long) : ResponseEntity<Any> {
        listingService.deleteById(id)
        return ResponseEntity.ok("Listing with id: $id deleted successfully")
    }

}