package com.example.diplomnabackend.auth

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationRequest (

    val email: String,
    val password: String

) {

}
