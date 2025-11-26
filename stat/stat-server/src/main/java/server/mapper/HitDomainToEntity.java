package server.mapper;

import server.domain.Hit;
import server.entity.HitEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HitDomainToEntity {

    HitEntity domainToEntity(Hit hit);
}
