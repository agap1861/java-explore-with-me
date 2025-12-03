package ewm.user.storage;

import ewm.user.domain.User;
import ewm.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByIdIn(List<Long> ids);

    @Query(value = "SELECT * FROM users ORDER BY ID ASC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<UserEntity> getByFilter(Integer from, Integer size);

    boolean existsByEmail(String email);
}
