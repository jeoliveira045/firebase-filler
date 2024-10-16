package web.fletcher.fletcherdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Configuration
public class WebfletcherConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpEntity httpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("X-Rapidapi-host", "mangaverse-api.p.rapidapi.com");
        httpHeaders.set("X-Rapidapi-key", "16368228ddmsh46d9e2cbb67c9a5p175e79jsn929dbf4d99f7");

        HttpEntity entity = new HttpEntity(httpHeaders);

        return entity;
    }
}
