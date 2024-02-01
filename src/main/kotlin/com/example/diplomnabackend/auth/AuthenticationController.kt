package com.example.diplomnabackend.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController (
    private val authenticationService: AuthenticationService

) {

    //ToDO: Add a check if authenticated to be able to reset token and skip welcome screen if logged in

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest) :ResponseEntity<AuthenticationResponse> {

        return ResponseEntity.ok(authenticationService.register(request))

    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest) :ResponseEntity<AuthenticationResponse> {

        return ResponseEntity.ok(authenticationService.authenticate(request))

    }

}