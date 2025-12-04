package ewm.user.mapper;

import ewm.user.domain.User;
import ewm.user.dto.NewUserRequest;
import ewm.user.dto.UserDto;
import ewm.user.dto.UserShortDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDomainDto {

    User toDomain(NewUserRequest dto);

    UserDto toDto(User user);

    UserShortDto toShortDto(User user);


}
