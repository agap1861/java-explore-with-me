package ewm.user.mapper;

import ewm.user.domain.User;
import ewm.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDomainToEntity {
    User domainToEntity(UserEntity user);

    UserEntity domainToEntity(User user);

}
