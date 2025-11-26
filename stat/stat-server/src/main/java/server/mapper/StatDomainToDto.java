package server.mapper;

import server.domain.Stat;
import dto.StatDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatDomainToDto {

    StatDto domainToDto(Stat stat);
}
