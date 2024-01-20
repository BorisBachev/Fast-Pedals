package com.example.diplomnabackend.auth

class AuthenticationResponse (
    val jwt: String
) {
    companion object {
        fun build(token: String): AuthenticationResponse {
            return AuthenticationResponse(token)
        }
    }

}
