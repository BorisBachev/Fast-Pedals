package com.example.diplomnabackend.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class JwtService (

    private val key: String = "6E26C7DAD8813374FB1BBBD2f9E3E68EA87C650A956661A303F17BB34675BD66"

) {
    fun extractUsername(jwt: String): String {
        return extractClaim(jwt, Claims::getSubject)
    }

    fun <T> extractClaim(jwt: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(jwt)
        return claimsResolver(claims)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 48))
            .signWith(getSignInKey(), io.jsonwebtoken.SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(mapOf(), userDetails)
    }

    fun extractAllClaims(jwt: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).getBody()
    }

    private fun getSignInKey(): Key {

        val keyBytes: ByteArray = Decoders.BASE64.decode(key)

        return Keys.hmacShaKeyFor(keyBytes)
    }

}
