package server.mapper;

import server.domain.Hit;
import dto.HitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HitDtoToDomain {

    Hit dtoToDomain(HitDto dto);
}
