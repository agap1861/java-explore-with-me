package Client;

import dto.HitDto;
import dto.StatDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class StatClient {
    private RestClient restClient;
    private String statUrl;

    public StatClient(RestClient restClient, @Value("http://localhost:9090") String statUrl) {
        this.restClient = restClient;
        this.statUrl = statUrl;
    }

    public void postStat(HitDto dto) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(statUrl + "/hit")
                .build()
                .toUri();
        restClient
                .post()
                .uri(uri)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public List<StatDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        URI uri = UriComponentsBuilder
                .fromHttpUrl(statUrl + "/stats")
                .queryParam("start", start.format(format))
                .queryParam("end", end.format(format))
                .queryParam("uris", uris.toArray())
                .queryParam("unique", unique)
                .build()
                .toUri();
        return restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StatDto>>() {
                });

    }


}
