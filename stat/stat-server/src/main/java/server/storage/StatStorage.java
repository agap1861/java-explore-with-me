package server.storage;

import server.controller.RequestFilterStat;
import server.domain.Hit;
import server.domain.Stat;

import java.util.List;

public interface StatStorage {

    void postHit(Hit hit);

    List<Stat> getStatByUnuniqueIpAndUris(RequestFilterStat requestFilterStat);

    List<Stat> getStatByUniqueIpANdUris(RequestFilterStat requestFilterStat);

    List<Stat> getStatByUnuniqueIpAndWithoutUris(RequestFilterStat requestFilterStat);

    List<Stat> getStatByUniqueIpAndWithoutUris(RequestFilterStat requestFilterStat);

    List<Stat> testAll();


}
