package web.fletcher.fletcherdata.service;

import com.google.api.Http;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.fletcher.fletcherdata.domain.PageRequest;
import org.springframework.http.HttpHeaders;

@Service
@AllArgsConstructor
@Slf4j
public class ApiService {

    private final RestTemplate restTemplate;

    private final HttpEntity entity;

    public PageRequest getPageRequest(){

        String url = "https://mangaverse-api.p.rapidapi.com/manga/fetch?page=3&genres=Fantasy&nsfw=false&type=all";
        ResponseEntity<PageRequest> response = restTemplate.exchange(url, HttpMethod.GET, entity, PageRequest.class);

        System.out.println(response.getBody().getData().size());

        return response.getBody();
    }
}
