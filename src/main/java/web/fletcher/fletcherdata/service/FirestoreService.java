package web.fletcher.fletcherdata.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web.fletcher.fletcherdata.domain.ChapterInformation;
import web.fletcher.fletcherdata.domain.ImageInformation;
import web.fletcher.fletcherdata.domain.MangaInformation;
import web.fletcher.fletcherdata.domain.PageRequest;

import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;
@Service
@AllArgsConstructor
public class FirestoreService {

    private Firestore db;

    private ApiService apiService;

    public void addInformation(Integer page) throws ExecutionException, InterruptedException{
        CollectionReference docRef = db.collection("manga-group");

        PageRequest<MangaInformation> mangaRequest = apiService.getPageRequest(page);

        mangaRequest.getData().forEach(document -> {
            docRef.document(document.getId()).set(document);
            this.getChapters(document.getId());
        });
    }

    public void getChapters(String mangaId){
        CollectionReference docRef = db.collection("manga-group");

        PageRequest<ChapterInformation> chaptersRequest = apiService.getChapters(mangaId);

        chaptersRequest.getData().forEach(chapterInformation -> {
            docRef.document(mangaId).collection("chapters").document(chapterInformation.getId()).set(chapterInformation);
//            getImages(mangaId, chapterInformation.getId());
        });
    }

    public void getImages(String mangaId, String chapterId) throws URISyntaxException {
        CollectionReference docRef = db.collection("manga-group");

        PageRequest<ImageInformation> imagesRequest = apiService.getImages(chapterId);

        imagesRequest.getData().forEach(imageInformation -> {
            docRef.document(mangaId).collection("chapters").document(chapterId).collection("images").document(imageInformation.getIndex().toString()).set(imageInformation);
        });
    }

    public void createCollection(){
        CollectionReference collectionReference = db.collection("manga-group2");

        Map<String, Object> pessoa = new HashMap<>();
        pessoa.put("id", "");
        pessoa.put("nome", "João");
        pessoa.put("idade", 18);

        collectionReference.document().set(pessoa);
    }

    public void deleteAll(){
        CollectionReference docRef = db.collection("manga-group");

        docRef.listDocuments().forEach(DocumentReference::delete);


    }

    public List<MangaInformation> findAll() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> mangaList = db.collection("manga-group").get();

        List<MangaInformation> mangaInformationList = new ArrayList<>();
        mangaList.get().getDocuments().forEach(document -> {
            mangaInformationList.add(document.toObject(MangaInformation.class));
        });

        return mangaInformationList;
    }
}
