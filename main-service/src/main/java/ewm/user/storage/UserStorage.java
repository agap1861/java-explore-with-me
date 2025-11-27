package ewm.user.storage;


import ewm.user.domain.User;

import java.util.List;

public interface UserStorage {

    User postUser(User user);

    List<User> getUserByFilter(Integer from, Integer size);

    List<User> getUsersByIds(List<Long> ids);

    boolean existUserById(Long userId);

    void deleteByUserId(Long userId);
}
