package fitness.fitness;


public interface UserService {
 User findByUsername(String username);

 User save(UserDto userDto);

}
