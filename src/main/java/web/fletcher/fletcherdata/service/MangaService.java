package web.fletcher.fletcherdata.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web.fletcher.fletcherdata.domain.MangaInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class MangaService {
    private Firestore db;

    public List<MangaInformation> findAll() throws InterruptedException, ExecutionException{
        ApiFuture<QuerySnapshot> collectionReference = db.collection("manga-group").get();

        List<MangaInformation> mangaInformationList = new ArrayList<>();

        collectionReference.get().getDocuments().forEach(queryDocumentSnapshot -> {
            mangaInformationList.add(queryDocumentSnapshot.toObject(MangaInformation.class));
        });
        
        return mangaInformationList;
    }

    public MangaInformation findById(String mangaId) throws ExecutionException, InterruptedException {
        return db.collection("manga-group").document(mangaId).get().get().toObject(MangaInformation.class);
    }

}
