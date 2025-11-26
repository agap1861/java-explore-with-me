package server.storage;


import server.domain.Stat;
import server.entity.HitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface StatJpaRepository extends JpaRepository<HitEntity, Long> {

    @Query("SELECT new server.domain.Stat(h.app, h.uri, COUNT(h.ip))  " +
            "FROM HitEntity h " +
            "WHERE (timestamp BETWEEN ?1 AND ?2) " +
            "AND uri IN ?3 " +
            "GROUP BY h.app, h.uri")
    List<Stat> getStatByUnuniqueIpAndUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new server.domain.Stat(h.app, h.uri, COUNT(DISTINCT h.ip))  " +
            "FROM HitEntity h " +
            "WHERE (timestamp BETWEEN ?1 AND ?2) " +
            "AND uri IN ?3 " +
            "GROUP BY h.app, h.uri ")
    List<Stat> getStatByUniqueIpANdUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new server.domain.Stat(h.app, h.uri, COUNT(h.ip)) " +
            "FROM HitEntity h " +
            "WHERE (timestamp BETWEEN ?1 AND ?2) " +
            "GROUP BY h.app, h.uri")
    List<Stat> getStatByUnuniqueIpAndWithoutUris(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new server.domain.Stat(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM HitEntity h " +
            "WHERE (timestamp BETWEEN ?1 AND ?2) " +
            "GROUP BY h.app, h.uri")
    List<Stat> getStatByUniqueIpAndWithoutUris(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new server.domain.Stat(h.app, h.uri, COUNT(h.ip)) FROM HitEntity h GROUP BY h.app, h.uri")
    List<Stat> testAll();


}
