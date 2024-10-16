package web.fletcher.fletcherdata.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.fletcher.fletcherdata.service.FirestoreService;

import java.util.concurrent.ExecutionException;

@RestController
//@AllArgsConstructor
@RequestMapping("/preenche-firestore")
public class PageRequestRest {

    @Autowired
    private FirestoreService firestoreService;

    @GetMapping("/preencher")
    public void preencheFireStore() throws ExecutionException, InterruptedException {
        firestoreService.addInformation();
    }
}
