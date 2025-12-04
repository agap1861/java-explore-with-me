package server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.controller.RequestFilterStat;
import server.domain.Hit;
import server.domain.Stat;
import server.exception.ValidateException;
import server.storage.StatStorage;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final StatStorage storage;

    @Override
    public void postHit(Hit hit) {
        storage.postHit(hit);

    }

    @Override
    public List<Stat> getStat(RequestFilterStat request) throws ValidateException {

        if (request.getStart().isAfter(LocalDateTime.now()) || request.getStart().isAfter(request.getEnd())) {
            throw new ValidateException("not correct time");
        }

        if (request.getUnique()) {
            if (request.getUris().size() == 1 && request.getUris().getFirst().equals("/events")) {
                return storage.getAllUnique();

            } else if (request.getUris().isEmpty()) {
                return storage.getStatByUniqueIpAndWithoutUris(request);
            } else {
                return storage.getStatByUniqueIpANdUris(request);
            }
        } else {
            if (request.getUris().size() == 1 && request.getUris().getFirst().equals("/events")) {
                return storage.getAllUnUnique();

            } else if (request.getUris().isEmpty()) {
                return storage.getStatByUnuniqueIpAndWithoutUris(request);
            } else {
                return storage.getStatByUnuniqueIpAndUris(request);
            }
        }
    }
}
