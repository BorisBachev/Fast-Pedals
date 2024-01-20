package com.example.diplomnabackend.auth

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationResponse (
    val jwt: String
) {
    companion object {
        fun build(token: String): AuthenticationResponse {
            return AuthenticationResponse(token)
        }
    }

}
