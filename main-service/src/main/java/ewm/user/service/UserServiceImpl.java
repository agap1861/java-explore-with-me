package ewm.user.service;

import ewm.exception.ConditionsNotMetException;
import ewm.exception.NotFoundException;
import ewm.user.domain.User;
import ewm.user.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Override
    public User postUser(User user) throws ConditionsNotMetException {
        checkEmailExists(user.getEmail());
        return userStorage.save(user);
    }

    @Override
    public List<User> getUsers(List<Long> ids, Integer from, Integer size) {
        if (ids.isEmpty()) {
            return userStorage.getUserByFilter(from, size);
        } else {
            return userStorage.getUsersByIds(ids);
        }
    }

    @Override
    public void deleteUserById(Long userId) throws NotFoundException {
        validateUserId(userId);
        userStorage.delete(userId);
    }

    @Override
    public User getUserById(Long userId) throws NotFoundException {
        return userStorage.getById(userId).orElseThrow(() -> new NotFoundException("user with id " + userId + "not found"));
    }

    @Override
    public boolean existById(Long userId) {
        return userStorage.existById(userId);
    }

    private void validateUserId(Long userId) throws NotFoundException {
        if (userId == null) {
            throw new IllegalArgumentException("User id can not be null");
        }
        if (!userStorage.existById(userId)) {
            throw new NotFoundException("user with id " + userId + "dose not exist");
        }

    }

    private void checkEmailExists(String email) throws ConditionsNotMetException {
        if (userStorage.existsByEmail(email)) {
            throw new ConditionsNotMetException("this email already busy");
        }
    }

}
