package com.example.diplomnabackend.config
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.lang.NonNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter (

    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService

) : OncePerRequestFilter() {

    override fun doFilterInternal(@NonNull request: HttpServletRequest,
                                  @NonNull response: HttpServletResponse, @NonNull filterChain: FilterChain) {

        val header = request.getHeader("Authorization")
        val jwt: String
        val email: String
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }
        /*val authentication = getAndValidateAuthentication(header)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)*/
        jwt = header.substring(7)
        email = jwtService.extractUsername(jwt)
        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(email)
            if (jwtService.isTokenValid(jwt, userDetails)) {
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getAndValidateAuthentication(header: String): Authentication? {
        val token = header.replace("Bearer ", "")
        val signingKey = "signingKey"
        val parsedToken = Jwts.parser()
            .setSigningKey(signingKey.toByteArray())
            .parseClaimsJws(token)
        val username = parsedToken
            .body
            .subject
        val authorities = (parsedToken.body["roles"] as List<*>)
            .stream()
            .map { authority: Any? -> SimpleGrantedAuthority(authority as String?) }
            .collect(Collectors.toList())
        return if (username != null) {
            UsernamePasswordAuthenticationToken(username, null, authorities)
        } else null
    }

}