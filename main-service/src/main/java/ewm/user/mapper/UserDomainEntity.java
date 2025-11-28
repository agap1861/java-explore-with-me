package ewm.user.mapper;

import ewm.core.BaseDomainEntityMapper;
import ewm.user.domain.User;
import ewm.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDomainEntity extends BaseDomainEntityMapper<User, UserEntity> {


}
