package com.example.diplomnabackend.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import com.example.diplomnabackend.entity.enums.Role

@Entity
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @Column(nullable = false, unique = true)
    private var name: String,

    @Column(nullable = false, unique = true)
    private var email: String,

    @Column(nullable = false)
    private var passw: String,

    @Column(nullable = false)
    private var fullName: String,

    @Column(nullable = false)
    private var phoneNumber: String,

    private var profilePicture: String?,

    @Enumerated(EnumType.STRING)
    private var role: Role,

    private var fcm: String?,

    @OneToMany
    private var listings: List<Listing>? = mutableListOf(),

    @OneToMany
    private var favourites: List<Favourite> = mutableListOf()

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

    fun getRole(): Role = role
    fun setRole(role: Role) {
        this.role = role
    }

    fun getFcm(): String? = fcm

    fun setFcm(fcm: String) {
        this.fcm = fcm
    }

    fun getProfilePicture(): String? = profilePicture
    fun setProfilePicture(profilePicture: String?) {
        this.profilePicture = profilePicture
    }
    fun getPhoneNumber(): String = phoneNumber
    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }
    fun getFullName(): String = fullName
    fun setFullName(fullName: String) {
        this.fullName = fullName
    }
    fun getEmail(): String = email
    fun setEmail(email: String) {
        this.email = email
    }
    fun getName(): String = name
    fun setName(name: String) {
        this.name = name
    }
    fun getId(): Long = id
    fun getPassw(): String = passw
    fun setPassw(passw: String) {
        this.passw = passw
    }

    companion object {
        fun build(email: String, password: String, role: Role): User {
            var user: User = noArgConstructor()
            user.setEmail(email)
            user.setPassw(password)
            user.setRole(role)
            return user
        }
        fun build(email: String, password: String, role: Role, name: String, fullName: String, phoneNumber: String, profilePicture: String?): User {
            var user: User = noArgConstructor()
            user.setEmail(email)
            user.setPassw(password)
            user.setRole(role)
            user.setName(name)
            user.setFullName(fullName)
            user.setPhoneNumber(phoneNumber)
            user.setProfilePicture(profilePicture)
            return user
        }

        private fun noArgConstructor(): User {
            return User(
                name = "",
                email = "",
                passw = "",
                fullName = "",
                phoneNumber = "",
                profilePicture = "",
                role = Role.USER,
                fcm = ""
            )
        }
    }

}
