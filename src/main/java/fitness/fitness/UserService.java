package fitness.fitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }
    public boolean isUsernameExists(String username) {
       
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }
   
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public String getRoleByUsername(String username) {
        return userRepository.findRoleByUsername(username);
    }
    
}
