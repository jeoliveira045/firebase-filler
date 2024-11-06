package web.fletcher.fletcherdata.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.fletcher.fletcherdata.domain.ImageInformation;
import web.fletcher.fletcherdata.service.ImagesService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageInformationRest {
    private ImagesService imagesService;

    @GetMapping("/{mangaId}/{chapterId}")
    public ResponseEntity<List<ImageInformation>> findAllImagesByChapter(@PathVariable String mangaId, @PathVariable String chapterId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(imagesService.findAllImagesByChapter(mangaId, chapterId));
    }
}
