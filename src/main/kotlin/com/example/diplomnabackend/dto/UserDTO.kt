package com.example.diplomnabackend.dto

import lombok.Data

@Data
class UserDTO (

    val id: Long,
    var username: String,
    var email: String,
    var password: String,
    var fullName: String,
    var phoneNumber: String,
    var profilePicture: String?

)