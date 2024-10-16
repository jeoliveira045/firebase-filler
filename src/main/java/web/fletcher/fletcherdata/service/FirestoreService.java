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

import java.util.concurrent.ExecutionException;
@Service
@AllArgsConstructor
public class FirestoreService {

    private Firestore db;

    private ApiService apiService;

    public void addInformation() throws ExecutionException, InterruptedException{
        CollectionReference docRef = db.collection("manga-group");

        PageRequest pageRequest = apiService.getPageRequest();

        pageRequest.getData().forEach(docRef::add);
    }
}
