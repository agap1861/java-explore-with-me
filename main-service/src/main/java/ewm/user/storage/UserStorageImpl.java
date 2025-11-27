package ewm.user.storage;

import ewm.user.domain.User;
import ewm.user.entity.UserEntity;
import ewm.user.mapper.UserDomainToEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserStorageImpl implements UserStorage {
    private final UserJpaRepository storage;
    private final UserDomainToEntity mapper;

    @Override
    public User postUser(User user) {
        UserEntity userEntity = storage.save(mapper.domainToEntity(user));
        return mapper.domainToEntity(userEntity);
    }

    @Override
    public List<User> getUserByFilter(Integer from, Integer size) {
        List<UserEntity> userEntities = storage.getByFilter(from, size);
        return userEntities.stream()
                .map(mapper::domainToEntity)
                .toList();
    }

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        List<UserEntity> userEntities = storage.findByIdIn(ids);
        return userEntities.stream()
                .map(mapper::domainToEntity)
                .toList();
    }

    @Override
    public boolean existUserById(Long userId) {
        return storage.existsById(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        storage.deleteById(userId);
    }
}
