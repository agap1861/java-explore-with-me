package client;

import dto.HitDto;
import dto.StatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class StatClient {
    private RestClient restClient;
    private String statUrl;

    public StatClient(RestClient restClient, String statUrl) {
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
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(statUrl + "/stats")
                .queryParam("start", start.format(format))
                .queryParam("end", end.format(format))
                .queryParam("unique", unique);

        if (uris != null && !uris.isEmpty()) {
            for (String uri : uris) {
                builder.queryParam("uris", uri);
            }
        }
        URI uri = builder.build().toUri();
        return restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StatDto>>() {
                });

    }


}
