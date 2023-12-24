package com.example.diplomnabackend.controller

import com.example.diplomnabackend.dto.BikeDTO
import com.example.diplomnabackend.service.BikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bike")
class BikeController (
    private val bikeService: BikeService
) {

    @GetMapping
    fun getAllBikes() : ResponseEntity<Any>{
        return ResponseEntity.ok(bikeService.findAll())
    }

    @GetMapping("/{id}")
    fun getBikeById(@PathVariable id: Long) : ResponseEntity<Any>{
        return ResponseEntity.ok(bikeService.findById(id))
    }

    @PostMapping
    fun saveBike(@RequestBody bikeDTO: BikeDTO) : ResponseEntity<Any>{
        return ResponseEntity.ok(bikeService.save(bikeDTO))
    }

    @PutMapping("/{id}")
    fun updateBike(@PathVariable id: Long, @RequestBody updatedBikeDTO: BikeDTO) : ResponseEntity<Any>{
        return ResponseEntity.ok(bikeService.update(id, updatedBikeDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteBike(@PathVariable id: Long) : ResponseEntity<Any>{
        bikeService.deleteById(id)
        return ResponseEntity.ok("Bike with id: $id deleted successfully")
    }

}