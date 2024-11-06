package web.fletcher.fletcherdata.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import web.fletcher.fletcherdata.domain.ImageInformation;

import javax.imageio.stream.ImageInputStreamImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class ImagesService {
    private Firestore db;

    public List<ImageInformation> findAllImagesByChapter(String mangaId, String chapterId) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = db.collection("manga-group").document(mangaId).collection("chapters").document(chapterId).collection("images");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = collectionReference.get();

        List<ImageInformation> imageInformationList = new ArrayList<>();
        querySnapshotApiFuture.get().getDocuments().forEach(document -> {
            imageInformationList.add(document.toObject(ImageInformation.class));
        });

        imageInformationList.sort(Comparator.comparing(ImageInformation::getIndex));

        return imageInformationList;

    }
}
