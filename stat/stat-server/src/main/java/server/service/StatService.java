package server.service;


import server.controller.RequestFilterStat;
import server.domain.Hit;
import server.domain.Stat;
import server.exception.ValidateException;

import java.util.List;

public interface StatService {

    void postHit(Hit hit);

    List<Stat> getStat(RequestFilterStat request) throws ValidateException;
}
