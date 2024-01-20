package com.example.diplomnabackend.dto

import lombok.Data

@Data
data class UserDTO (

    val id: Long,
    var name: String,
    var email: String,
    var passw: String,
    var fullName: String,
    var phoneNumber: String,
    var profilePicture: String?

)