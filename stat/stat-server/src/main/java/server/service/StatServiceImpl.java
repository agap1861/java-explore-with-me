package server.service;

import server.controller.RequestFilterStat;
import server.domain.Hit;
import server.domain.Stat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.storage.StatStorage;

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
    public List<Stat> getStat(RequestFilterStat request) {
        if (request.getUnique()) {
            if (request.getUris().isEmpty()) {
                return storage.getStatByUniqueIpAndWithoutUris(request);
            } else {
                return storage.getStatByUniqueIpANdUris(request);
            }
        } else {
            if (request.getUris().isEmpty()) {
                return storage.getStatByUnuniqueIpAndWithoutUris(request);
            } else {
                return storage.getStatByUnuniqueIpAndUris(request);
            }
        }
    }
}
