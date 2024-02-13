package dataAccess;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class InMemoryUserDao {
    private static InMemoryUserDao instance;
    private List<User> users;

    private InMemoryUserDao() {
        users = new ArrayList<>();
        users.add(new User("user", "123456")); //örnek bir user ve password
    }

    public static InMemoryUserDao getInstance() {
        if (instance == null) {
            instance = new InMemoryUserDao();
        }
        return instance;
    }

    public boolean saveUser(User user) {
        if (isUsernameAvailable(user.getUsername())) {
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByUsername(String username) {// Kullanıcıyı kullanıcı adına göre getirme işlemini gerçekleştir
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public boolean isUsernameAvailable(String username) { // Kullanıcı adının kullanılabilir olup olmadığını kontrol etme işlemini gerçekleştir
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
