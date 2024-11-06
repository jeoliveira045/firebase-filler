package web.fletcher.fletcherdata.service;

import com.google.api.gax.paging.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.fletcher.fletcherdata.domain.ChapterInformation;
import web.fletcher.fletcherdata.domain.ImageInformation;
import web.fletcher.fletcherdata.domain.MangaInformation;
import web.fletcher.fletcherdata.domain.PageRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.NameValuePair;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public PageRequest<ImageInformation> getImages(String chapterId) throws URISyntaxException {
        String url = "https://mangaverse-api.p.rapidapi.com/manga/image?id=" + chapterId;
        ResponseEntity<PageRequest<ImageInformation>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<PageRequest<ImageInformation>>(){});

        response.getBody().getData().forEach(imageInformation -> {
            String originalImageLink = imageInformation.getLink();

            try {
                URIBuilder uriBuilder = new URIBuilder(originalImageLink);
                Map<String, String> params = uriBuilder.getQueryParams().stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
                String imageLink = "https://" + uriBuilder.getHost() + uriBuilder.getPath();
                ResponseEntity<byte[]> imageBase64 = restTemplate.exchange(imageLink, HttpMethod.GET, entity, new ParameterizedTypeReference<byte[]>() {}, params);
                if (response.getStatusCode() == HttpStatus.OK) {
                    byte[] imageBytes = imageBase64.getBody();

                    // Converte os bytes da imagem para uma string Base64
                    imageInformation.setLink(Base64.getEncoder().encodeToString(imageBytes));
                } else {
                    throw new RuntimeException("Falha ao obter a imagem. Código de resposta: " + response.getStatusCode());
                };
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        });
        return response.getBody();
    }
}
