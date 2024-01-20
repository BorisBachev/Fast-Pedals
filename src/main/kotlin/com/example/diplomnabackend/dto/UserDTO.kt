package com.example.diplomnabackend.dto

data class UserDTO (

    val id: Long,
    var name: String,
    var email: String,
    var passw: String,
    var fullName: String,
    var phoneNumber: String,
    var profilePicture: String?

)