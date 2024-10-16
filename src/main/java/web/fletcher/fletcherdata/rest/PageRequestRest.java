package web.fletcher.fletcherdata.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.fletcher.fletcherdata.service.FirestoreService;

import java.util.concurrent.ExecutionException;

@RestController
//@AllArgsConstructor
@RequestMapping("/preenche-firestore")
public class PageRequestRest {

    @Autowired
    private FirestoreService firestoreService;

    @GetMapping("/preencher/{page}")
    public void preencheFireStore(@PathVariable Integer page) throws ExecutionException, InterruptedException {
        firestoreService.addInformation(page);
    }

    @DeleteMapping("/deletar-todos")
    public void deletarTodos(){
        firestoreService.deleteAll();
    }

    @GetMapping("/create-collection")
    public void createCollection() throws ExecutionException, InterruptedException {
        firestoreService.createCollection();
    }
}
