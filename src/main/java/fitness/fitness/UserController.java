package fitness.fitness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/users")
public class UserController {
     private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
   

    
    @PostMapping("/register")
    public RedirectView addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if (userService.isUsernameExists(user.getUsername())) {
            redirectAttributes.addFlashAttribute("errorMessage", "This username is already exist.");
            return new RedirectView("/register");
        } else {
            user.setRole("USER");
            userService.registerUser(user);
            return new RedirectView("/");
        }
    }
    @PostMapping("/login")
    public RedirectView login(@RequestParam("username") String username, @RequestParam("password") String password,
    RedirectAttributes redirectAttributes) {
    User user = userService.getUserByUsernameAndPassword(username, password);
    
    if (user != null) {
        user.setRole(userService.getRoleByUsername(username));
        if (user.getRole().equals("ADMIN")) {
            return new RedirectView("/admin");
        } else {
            return new RedirectView("/user");
        }
    } else {
        redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password.");
        return new RedirectView("/login"); 
    }
}

    
}
