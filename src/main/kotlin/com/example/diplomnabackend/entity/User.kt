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
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var passw: String,

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

    @Override
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(GrantedAuthority { role.name })
    }

    @Override
    override fun getPassword(): String = passw

    @Override
    override fun getUsername(): String = email

    @Override
    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    @Override
    override fun isCredentialsNonExpired(): Boolean = true

    @Override
    override fun isEnabled(): Boolean = true
}
