package ewm.user.service;

import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.user.domain.User;

import java.util.List;

public interface UserService {
    User postUser(User user) throws ConditionsNotMetException;

    List<User> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUserById(Long userId) throws NotFoundException;

    User getUserById(Long userId) throws NotFoundException;

    boolean existById(Long userId);
}
