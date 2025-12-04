package ewm.user.mapper;

import ewm.user.domain.User;
import ewm.user.dto.NewUserRequest;
import ewm.user.dto.UserDto;
import ewm.user.dto.UserShortDto;
import ewm.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class UserMapperTest {
    private final UserDomainDto domainDtoMapper = Mappers.getMapper(UserDomainDto.class);
    private final UserDomainEntity domainEntityMapper = Mappers.getMapper(UserDomainEntity.class);

    @Test
    public void toDomainShouldCorrectlyMap() {
        NewUserRequest dto = new NewUserRequest();
        dto.setName("name");
        dto.setEmail("email@mail.ru");
        User user = domainDtoMapper.toDomain(dto);

        assertNull(user.getId());
        assertEquals(dto.getName(), user.getName());
        assertEquals(dto.getEmail(), user.getEmail());
    }

    @Test
    public void shouldCorrectlyMapDomainToDto() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setEmail("email@emal.ru");

        UserDto dto = domainDtoMapper.toDto(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getName(), dto.getName());
        assertEquals(user.getEmail(), dto.getEmail());

    }

    @Test
    public void shouldCorrectlyMapDomainToShortDto() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setEmail("email@emal.ru");

        UserShortDto dto = domainDtoMapper.toShortDto(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getName(), dto.getName());
    }

    @Test
    public void shouldCorrectlyMapDomainToEntity() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setEmail("email@emal.ru");

        UserEntity entity = domainEntityMapper.toEntity(user);

        assertEquals(user.getId(), entity.getId());
        assertEquals(user.getName(), entity.getName());
        assertEquals(user.getEmail(), entity.getEmail());
    }

    @Test
    public void shouldCorrectlyMapEntityToDomain() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setEmail("email@email.ro");

        User user = domainEntityMapper.toDomain(entity);

        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getName(), user.getName());
        assertEquals(entity.getEmail(), user.getEmail());
    }


}