package server.mapper;

import org.mapstruct.Mapper;
import server.domain.Hit;
import server.entity.HitEntity;

@Mapper(componentModel = "spring")
public interface HitDomainToEntity {

    HitEntity domainToEntity(Hit hit);
}
