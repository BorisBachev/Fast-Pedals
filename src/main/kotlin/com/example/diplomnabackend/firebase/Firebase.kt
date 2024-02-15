package com.example.diplomnabackend.firebase

/*import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream


@Configuration
class Firebase {

    @Bean
    fun firebaseApp(): FirebaseApp {

        val serviceAccount = FileInputStream("/C:/Users/yb/IdeaProjects/DiplomnaBackend/fast-pedals-firebase-adminsdk-dueve-105a73ebda.json")

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp?): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }

}*/