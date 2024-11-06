package web.fletcher.fletcherdata.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web.fletcher.fletcherdata.domain.ChapterInformation;
import web.fletcher.fletcherdata.domain.MangaInformation;
import web.fletcher.fletcherdata.domain.dto.MangaInformationDTO;

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

    public MangaInformationDTO findById(String mangaId) throws ExecutionException, InterruptedException {
        MangaInformationDTO mangaInformationDTO = db.collection("manga-group").document(mangaId).get().get().toObject(MangaInformationDTO.class);

        db.collection("manga-group").document(mangaId).collection("chapters").get().get().getDocuments().forEach(queryDocumentSnapshot -> {
            assert mangaInformationDTO != null;
            mangaInformationDTO.getChapterInformationList().add(queryDocumentSnapshot.toObject(ChapterInformation.class));
        });

        return mangaInformationDTO;
    }

}
