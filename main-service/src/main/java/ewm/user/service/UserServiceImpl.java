package ewm.user.service;

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
    public User postUser(User user) {
        return userStorage.postUser(user);
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
        userStorage.deleteByUserId(userId);
    }
    private void validateUserId(Long userId) throws NotFoundException {
        if (userId == null){
            throw new IllegalArgumentException("User id can not be null");
        }
        if (!userStorage.existUserById(userId)){
            throw new NotFoundException("user with id "  + userId + "dose not exist");
        }

    }

}
