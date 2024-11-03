package web.fletcher.fletcherdata.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.fletcher.fletcherdata.service.MangaService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/manga-information")
@AllArgsConstructor
public class MangaInformationRest {

    private MangaService mangaService;

    @GetMapping
    public ResponseEntity<?> findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(mangaService.findAll());
    }

    @GetMapping("/{mangaId}")
    public ResponseEntity<?> findById(@PathVariable String mangaId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(mangaService.findById(mangaId));
    }

}
