package fitness.fitness;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="user")
public class User {
 @Id
 @GeneratedValue
 private Long id;   
 private String username;
 private String password;
 private String role;


    public User(String username, String password,String role) {
        this.username = username;
        this.password = password;
        this.role = role;

        
    }
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
    public boolean hasRole(String role) {
        if(this.role==role)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
   
}
