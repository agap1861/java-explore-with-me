package ewm.user.storage;


import ewm.user.domain.User;
import ewm.user.entity.UserEntity;
import ewm.user.mapper.UserDomainEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserStorageImpl implements UserStorage {
    private final UserDomainEntity mapper;
    private final UserJpaRepository storage;

    @Override
    public List<User> getUserByFilter(Integer from, Integer size) {
        List<UserEntity> userEntities = storage.getByFilter(from, size);
        return userEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        List<UserEntity> userEntities = storage.findByIdIn(ids);
        return userEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public User save(User domain) {
        UserEntity entity = storage.save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        storage.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return storage.existsById(id);
    }

    @Override
    public Optional<User> getById(Long id) {
        Optional<UserEntity> entity = storage.findById(id);
        return entity.map(mapper::toDomain);
    }
}
