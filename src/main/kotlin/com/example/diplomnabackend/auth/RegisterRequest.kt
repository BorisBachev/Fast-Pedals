package com.example.diplomnabackend.auth

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class RegisterRequest (

    val name: String,
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val profilePicture: String?

) {

}
