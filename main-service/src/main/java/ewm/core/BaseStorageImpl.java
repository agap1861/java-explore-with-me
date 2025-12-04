package ewm.core;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseStorageImpl<Id, D, E> implements BaseStorage<Id, D> {
   /* private final BaseMapper<D, E> mapper;
    protected abstract  JpaRepository<E,Id> storage();


    @Override
    public D save(D domain) {
        E entity =  storage().save(mapper.toEntity(domain));
        return mapper.toDomain(entity);
    }

    @Override
    public void delete(Id id) {
        storage().deleteById(id);
    }

    @Override
    public boolean existById(Id id) {
        return storage().existsById(id);
    }

    @Override
    public Optional<D> getById(Id id) {
        Optional<E> entity = storage().findById(id);
        return entity.map(mapper::toDomain);
    }
}

class UserStorage extends BaseStorageImpl<Long, User, UserEntity> implements ewm.user.storage.UserStorage {

    public UserStorage(BaseMapper<User, UserEntity> mapper, JpaRepository<UserEntity, Long> storage) {
        super(mapper, storage);
    }

    @Override
    public List<User> getUserByFilter(Integer from, Integer size) {
        return List.of();
    }

    @Override
    public List<User> getUsersByIds(List<Long> ids) {
        return List.of();
    }*/
}

