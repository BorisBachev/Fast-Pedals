package com.example.diplomnabackend.controller

import com.example.diplomnabackend.dto.UserDTO
import com.example.diplomnabackend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController (
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers() : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findAll())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findById(id))
    }

    @PostMapping
    fun saveUser(@RequestBody userDTO: UserDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.save(userDTO))
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUserDTO: UserDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(userService.update(id, updatedUserDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) : ResponseEntity<Any> {
        userService.deleteById(id)
        return ResponseEntity.ok("User with id: $id deleted successfully")
    }

}