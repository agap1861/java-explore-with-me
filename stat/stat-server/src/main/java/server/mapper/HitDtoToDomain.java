package server.mapper;

import dto.HitDto;
import org.mapstruct.Mapper;
import server.domain.Hit;

@Mapper(componentModel = "spring")
public interface HitDtoToDomain {

    Hit dtoToDomain(HitDto dto);
}
