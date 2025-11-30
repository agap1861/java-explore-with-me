package ewm.user.mapper;

import ewm.user.domain.User;
import ewm.user.dto.NewUserRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;


class UserDomainDtoTest {
   UserDomainDto domainDtoMapper = Mappers.getMapper(UserDomainDto.class);

  /*  @BeforeEach
    public void createdInstance(){
        User user = new User();
        user.setId(1L);
        user.setName("nameUser");
        user.
    }*/

    @Test
    public void toDomainShouldCorrectlyMap(){
        NewUserRequest dto = new NewUserRequest();
        dto.setName("name");
        dto.setEmail("email@mail.ru");
        User user = domainDtoMapper.toDomain(dto);

        assertNull(user.getId());
        assertEquals(dto.getName(),user.getName());
        assertEquals(dto.getEmail(),user.getEmail());
    }

}