package ewm.user.storage;


import ewm.core.BaseStorage;
import ewm.user.domain.User;

import java.util.List;

public interface UserStorage extends BaseStorage<Long,User> {


    List<User> getUserByFilter(Integer from, Integer size);

    List<User> getUsersByIds(List<Long> ids);

}
