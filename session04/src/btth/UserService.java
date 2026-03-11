package btth;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    List<User> users=new ArrayList<>();
    public void addUser(User user) {
        if(user.getUsername().isBlank()) throw new IllegalArgumentException("Username cannot be empty");
        users.add(user);
    }
    public User findById(int id) {
        return users.stream()
                .filter(u->u.getId()==id)
                .findFirst()
                .orElse(null);
    }
    public boolean isValidEmail(String eamil){
        return eamil.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+");
    }
}
