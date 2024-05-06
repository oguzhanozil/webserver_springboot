package fitness.fitness;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    @Query("SELECT u FROM user u WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    User findByUsername(String username);
    @Query("SELECT u.role FROM user u WHERE u.username = :username")
    String findRoleByUsername(@Param("username") String username);
    
    
    
}
