package ewm.user.service;

import ewm.exception.NotFoundException;
import ewm.user.domain.User;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface UserService {
     User postUser(User user);

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUserById(Long userId) throws NotFoundException;
}
