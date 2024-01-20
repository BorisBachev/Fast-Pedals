package com.example.diplomnabackend.auth

data class RegisterRequest (

    val name: String,
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val profilePicture: String?

) {

}
