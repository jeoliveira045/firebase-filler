package web.fletcher.fletcherdata.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("classpath:/firebase/mangaverse-api-11ea6-firebase-adminsdk-1kl7l-93cad4a2fa.json")
    private Resource privateKey;

    @Bean
    public Firestore firestore() throws IOException {
        InputStream credentials = new ByteArrayInputStream(privateKey.getContentAsByteArray());
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .build();

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
        return FirestoreClient.getFirestore(firebaseApp);
    }

}
