package server.controller;

import dto.HitDto;
import dto.StatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import server.mapper.HitDtoToDomain;
import server.mapper.StatDomainToDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatController {
    private final StatService service;
    private final StatDomainToDto statMapper;
    private final HitDtoToDomain hitMapper;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void postHit(@RequestBody HitDto dto) {
        service.postHit(hitMapper.dtoToDomain(dto));
    }

    @GetMapping("/stats")
    public List<StatDto> getStats(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                  @RequestParam(defaultValue = "") List<String> uris, @RequestParam(defaultValue = "false") boolean unique) {
        RequestFilterStat requestFilterStat = RequestFilterStat.builder()
                .start(start)
                .end(end)
                .unique(unique)
                .uris(uris)
                .build();
        return service.getStat(requestFilterStat).stream()
                .map(statMapper::domainToDto)
                .toList();


    }

}
