package com.example.diplomnabackend.auth

data class AuthenticationResponse (
    val jwt: String
) {
    companion object {
        fun build(token: String): AuthenticationResponse {
            return AuthenticationResponse(token)
        }
    }

}
