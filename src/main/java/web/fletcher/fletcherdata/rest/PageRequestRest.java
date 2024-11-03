package web.fletcher.fletcherdata.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.fletcher.fletcherdata.service.FirestoreService;

import java.util.concurrent.ExecutionException;

@RestController
//@AllArgsConstructor
@RequestMapping("/manga")
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

    @GetMapping
    public ResponseEntity<?> findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(firestoreService.findAll());
    }

    @GetMapping("fill-chapter/{mangaId}/{mangaChapter}")
    public void fillChapter(@PathVariable String mangaId, @PathVariable String mangaChapter){
        firestoreService.getImages(mangaId, mangaChapter);
    }
}
