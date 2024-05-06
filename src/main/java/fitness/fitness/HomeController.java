package fitness.fitness;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
        }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/register")
    public String register()
    {
        return "register";
    }
    @GetMapping("/user")
    public String user()
    {
        return "user";
    }
    @GetMapping("/admin")
    public String admin()
    {
        return "admin";
    }
    
}
