package com.example.diplomnabackend.controller

import com.example.diplomnabackend.dto.ListingDTO
import com.example.diplomnabackend.dto.ListingNameDTO
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

    @PostMapping("/user")
    fun saveListingByUser(@RequestBody listingDTO: ListingNameDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.saveByUser(listingDTO))
    }

    @PutMapping("/{id}")
    fun updateListing(@PathVariable id: Long, @RequestBody updatedListingDTO: ListingDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(listingService.update(id, updatedListingDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteListing(@PathVariable id: Long) : ResponseEntity<Any> {
        listingService.deleteById(id)
        return ResponseEntity.ok("Listing with id: $id deleted successfully")
    }

    @GetMapping("/search")
    fun searchListings(
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?,
        @RequestParam(required = false) location: String?,
        @RequestParam(required = false) description: String?,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) brand: String?,
        @RequestParam(required = false) model: String?,
        @RequestParam(required = false) size: String?,
        @RequestParam(required = false) wheelSize: Int?,
        @RequestParam(required = false) frameMaterial: String?
    ): ResponseEntity<List<ListingDTO?>?> {
        val result = listingService.searchListings(
            title, minPrice, maxPrice, location, description, type, brand, model, size, wheelSize, frameMaterial
        )
        return ResponseEntity.ok(result)
    }

}