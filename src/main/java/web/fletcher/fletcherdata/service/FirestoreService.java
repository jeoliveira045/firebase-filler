package web.fletcher.fletcherdata.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.rpc.context.AttributeContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.fletcher.fletcherdata.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
@Service
@AllArgsConstructor
public class FirestoreService {

    private Firestore db;

    private ApiService apiService;

    public void addInformation(Integer page) throws ExecutionException, InterruptedException{
        CollectionReference docRef = db.collection("manga-group");

        PageRequest pageRequest = apiService.getPageRequest(page);

        pageRequest.getData().forEach(document -> {
            docRef.document(document.getId()).set(document);
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

//        PageRequest pageRequest = apiService.getPageRequest(page);

        docRef.listDocuments().forEach(DocumentReference::delete);


    }
//
//    public void createCollection(String collectionName){
//        db.bundleBuilder().
//    }
}
