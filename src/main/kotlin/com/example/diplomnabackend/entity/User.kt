package com.example.diplomnabackend.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import com.example.diplomnabackend.entity.enums.Role

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var fullName: String,

    @Column(nullable = false)
    var phoneNumber: String,

    var profilePicture: String?,

    @Enumerated(EnumType.STRING)
    var role: Role,

    @OneToMany
    var listings: List<Listing>? = mutableListOf(),

    @OneToMany
    var favourites: List<Favourite> = mutableListOf()

) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(GrantedAuthority { role.name })
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
