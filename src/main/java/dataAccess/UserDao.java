package dataAccess;

import entity.User;

public interface UserDao {
	boolean saveUser(User user);
    User getUserByUsername(String username);
    boolean isUsernameAvailable(String username);
}