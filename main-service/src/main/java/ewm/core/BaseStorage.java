package ewm.core;


import java.util.Optional;

public interface BaseStorage<Id, T> {
    T save(T domain);

    void delete(Id id);

    boolean existById(Id id);

    Optional<T> getById(Id id);
}



