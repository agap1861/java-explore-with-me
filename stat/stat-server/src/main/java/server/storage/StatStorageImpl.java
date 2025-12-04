package server.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.controller.RequestFilterStat;
import server.domain.Hit;
import server.domain.Stat;
import server.mapper.HitDomainToEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatStorageImpl implements StatStorage {
    private final StatJpaRepository repository;
    private final HitDomainToEntity hitMapper;


    @Override
    public void postHit(Hit hit) {
        repository.save(hitMapper.domainToEntity(hit));

    }

    @Override
    public List<Stat> getStatByUnuniqueIpAndUris(RequestFilterStat requestFilterStat) {
        return repository.getStatByUnuniqueIpAndUris(requestFilterStat.getStart(), requestFilterStat.getEnd(),
                requestFilterStat.getUris());
    }

    @Override
    public List<Stat> getStatByUniqueIpANdUris(RequestFilterStat requestFilterStat) {
        return repository.getStatByUniqueIpANdUris(requestFilterStat.getStart(), requestFilterStat.getEnd(),
                requestFilterStat.getUris());
    }

    @Override
    public List<Stat> getStatByUnuniqueIpAndWithoutUris(RequestFilterStat requestFilterStat) {
        return repository.getStatByUnuniqueIpAndWithoutUris(requestFilterStat.getStart(), requestFilterStat.getEnd());
    }

    @Override
    public List<Stat> getStatByUniqueIpAndWithoutUris(RequestFilterStat requestFilterStat) {
        return repository.getStatByUniqueIpAndWithoutUris(requestFilterStat.getStart(), requestFilterStat.getEnd());
    }

    @Override
    public List<Stat> testAll() {
        return repository.testAll();
    }
}
