package com.example.diplomnabackend.controller

import com.example.diplomnabackend.dto.FavouriteDTO
import com.example.diplomnabackend.service.FavouriteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/favourite")
class FavouriteController (
    private val favouriteService: FavouriteService
) {

    @GetMapping
    fun getAllFavourites() : ResponseEntity<Any> {
        return ResponseEntity.ok(favouriteService.findAll())
    }

    @GetMapping("/{id}")
    fun getFavouriteById(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(favouriteService.findById(id))
    }

    @PostMapping
    fun saveFavourite(@RequestBody favouriteDTO: FavouriteDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(favouriteService.save(favouriteDTO))
    }

    @PostMapping("/{id}")
    fun deleteFavourite(@PathVariable id: Long) : ResponseEntity<Any> {
        favouriteService.deleteById(id)
        return ResponseEntity.ok("Favourite with id: $id deleted successfully")
    }


}