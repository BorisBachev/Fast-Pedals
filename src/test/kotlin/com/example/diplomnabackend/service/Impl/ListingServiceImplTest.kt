package com.example.diplomnabackend.service.Impl

import com.example.diplomnabackend.entity.Bike
import com.example.diplomnabackend.entity.Listing
import com.example.diplomnabackend.entity.User
import com.example.diplomnabackend.entity.enums.BikeBrand
import com.example.diplomnabackend.entity.enums.BikeType
import com.example.diplomnabackend.entity.enums.Role
import com.example.diplomnabackend.repository.BikeRepository
import com.example.diplomnabackend.repository.FavouriteRepository
import com.example.diplomnabackend.repository.ListingRepository
import com.example.diplomnabackend.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

private const val LISTING_ID = 1L
private const val USER_ID = 1L
private const val BIKE_ID = 1L

private const val LISTING_TITLE = "Used mountain bike"
private const val LISTING_DESCRIPTION = "Used mountain bike for sale"
private const val LISTING_PRICE = 256.0
private const val LISTING_LOCATION = "Sofia"
private const val LISTING_DATE = "2022-12-12"
private val LISTING_IMAGES = listOf("image1", "image2")

private const val USER_EMAIL = "email@email.com"
private const val USER_NAME = "Vinetu"
private const val USER_PHONE_NUMBER = "1234567890"
private const val USER_FULLNAME = "Vinetu Venev"
private const val USER_PROFILE_PICTURE = "profilePicture"
private val USER_ROLE = Role.USER
private const val USER_PASSWORD = "password"
private const val USER_FCM = "fcm"

private val BIKE_BRAND = BikeBrand.CANNONDALE
private val BIKE_TYPE = BikeType.MOUNTAIN
private const val BIKE_MODEL = "Spectral"
private const val BIKE_SIZE = "L"
private const val BIKE_WHEEL_SIZE = 29
private const val BIKE_FRAME_MATERIAL = "Carbon"

@ExtendWith(SpringExtension::class)
class ListingServiceImplTest {

    private val mockListingRepository = Mockito.mock(ListingRepository::class.java)
    private val mockBikeRepository = Mockito.mock(BikeRepository::class.java)
    private val mockUserRepository = Mockito.mock(UserRepository::class.java)
    private val mockFavouriteRepository = Mockito.mock(FavouriteRepository::class.java)
    private val auth = Mockito.mock(Authentication::class.java)

    private lateinit var listingService: ListingServiceImpl

    @BeforeEach
    fun setUp() {
        listingService = ListingServiceImpl(mockListingRepository, mockBikeRepository, mockUserRepository, mockFavouriteRepository)
        `when`(auth.name).thenReturn(USER_EMAIL)
        SecurityContextHolder.getContext().authentication = auth
    }

    @Test
    fun getWholeListing() {
        // Arrange
        `when`(mockListingRepository.findById(LISTING_ID)).thenReturn(getListing())
        `when`(mockUserRepository.findByEmail(USER_EMAIL))
            .thenReturn(getUser())
        `when`(mockFavouriteRepository.existsByUserIdAndListingId(anyLong(), anyLong()))
            .thenReturn(false)
        val listing = listingService.getWholeListing(LISTING_ID)

        // Act
        listingService.getWholeListing(LISTING_ID)

        // Assert
        assertEquals(LISTING_ID, listing.id)
        assertEquals(LISTING_TITLE, listing.title)
        assertEquals(LISTING_DESCRIPTION, listing.description)
        assertEquals(LISTING_PRICE, listing.price)
        assertEquals(LISTING_LOCATION, listing.location)
        assertEquals(LISTING_DATE, listing.date)
        assertEquals(LISTING_IMAGES, listing.images)
        assertEquals(BIKE_TYPE, listing.type)
        assertEquals(BIKE_BRAND, listing.brand)
        assertEquals(BIKE_MODEL, listing.model)
        assertEquals(BIKE_SIZE, listing.size)
        assertEquals(BIKE_WHEEL_SIZE, listing.wheelSize)
        assertEquals(BIKE_FRAME_MATERIAL, listing.frameMaterial)
        assertEquals(BIKE_ID, listing.bikeId)
        assertEquals(USER_ID, listing.userId)
        assertEquals(USER_NAME, listing.name)
        assertEquals(USER_PHONE_NUMBER, listing.phoneNumber)
        assertEquals(false, listing.isFavourite)
        assertEquals(true, listing.isOwner)
    }

    private fun getListing(): Optional<Listing> {
        val user = getUser()
        val bike = Bike(BIKE_ID, BIKE_TYPE, BIKE_BRAND, BIKE_MODEL, BIKE_SIZE, BIKE_WHEEL_SIZE, BIKE_FRAME_MATERIAL)
        val listing = Listing(LISTING_ID, user, bike, LISTING_TITLE, LISTING_DESCRIPTION, LISTING_PRICE, LISTING_LOCATION, LISTING_DATE, LISTING_IMAGES)

        return Optional.of(listing)
    }

    private fun getUser(): User {
        val user = User(USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD, USER_FULLNAME, USER_PHONE_NUMBER, USER_PROFILE_PICTURE, USER_ROLE, USER_FCM)
        return user
    }
}