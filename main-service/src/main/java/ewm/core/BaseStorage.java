package ewm.core;


import java.util.Optional;

public interface BaseStorage<ID, T> {
    T save(T domain);

    void delete(ID id);

    boolean existById(ID id);

    Optional<T> getById(ID id);
}



