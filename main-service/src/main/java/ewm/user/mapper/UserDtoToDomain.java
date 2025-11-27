package ewm.user.mapper;

import ewm.user.domain.User;
import ewm.user.dto.NewUserRequest;
import ewm.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoToDomain {

     User dtoToDomain(NewUserRequest dto);

     UserDto domainToDto(User user);
}
