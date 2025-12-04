package server.mapper;

import dto.StatDto;
import org.mapstruct.Mapper;
import server.domain.Stat;

@Mapper(componentModel = "spring")
public interface StatDomainToDto {

    StatDto domainToDto(Stat stat);
}
