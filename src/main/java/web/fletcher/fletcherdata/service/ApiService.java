package web.fletcher.fletcherdata.service;

import com.google.api.gax.paging.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.fletcher.fletcherdata.domain.ChapterInformation;
import web.fletcher.fletcherdata.domain.ImageInformation;
import web.fletcher.fletcherdata.domain.MangaInformation;
import web.fletcher.fletcherdata.domain.PageRequest;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ApiService {

    private final RestTemplate restTemplate;

    private final HttpEntity entity;

    public PageRequest<MangaInformation> getPageRequest(Integer page){

        String url = "https://mangaverse-api.p.rapidapi.com/manga/fetch?page="+ page +"&genres=Fantasy&nsfw=false&type=all";
        ResponseEntity<PageRequest<MangaInformation>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<PageRequest<MangaInformation>>(){});

        System.out.println(response.getBody().getData().size());

        return response.getBody();
    }

    public PageRequest getManga(String mangaId){
        String url = "https://mangaverse-api.p.rapidapi.com/manga?id=" + mangaId;
        ResponseEntity<PageRequest> response = restTemplate.exchange(url, HttpMethod.GET, entity, PageRequest.class);
        System.out.println(response.getBody().getData().size());
        return response.getBody();
    }

    public PageRequest<ChapterInformation> getChapters(String mangaId){
        String url = "https://mangaverse-api.p.rapidapi.com/manga/chapter?id=" + mangaId;
        ResponseEntity<PageRequest<ChapterInformation>> response = restTemplate.exchange(url, HttpMethod.GET, entity,new ParameterizedTypeReference<PageRequest<ChapterInformation>>(){});
        System.out.println(Objects.requireNonNull(response.getBody()).getData().size());
        return response.getBody();
    }

    public PageRequest<ImageInformation> getImages(String chapterId){
        String url = "https://mangaverse-api.p.rapidapi.com/manga/image?id=" + chapterId;
        ResponseEntity<PageRequest<ImageInformation>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<PageRequest<ImageInformation>>(){});
        System.out.println(response.getBody().getData().size());
        return response.getBody();
    }
}
