package fitness.fitness;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

 @Autowired
 private UserDetailsService userDetailsService;

 private UserService userService;

 public UserController(UserService userService) {
  this.userService = userService;
 }

 @GetMapping("/home")
 public String home(Model model, Principal principal) {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     boolean isAuthenticated = authentication.isAuthenticated();
     model.addAttribute("isAuthenticated", isAuthenticated);
  UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
  model.addAttribute("userdetail", userDetails);
  boolean isAdmin = authentication.getAuthorities().stream()
  .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
  model.addAttribute("isAdmin", isAdmin);
  return "home";
 }
 @GetMapping("/admin")
 public String admin(Model model, UserDto userDto){
    return "admin";
    
 }
 

 @GetMapping("/login")
 public String login(Model model, UserDto userDto) {

  model.addAttribute("user", userDto);
  return "login";
 }

 @GetMapping("/register")
 public String register(Model model, UserDto userDto) {
  model.addAttribute("user", userDto);
  return "register";
 }

 @PostMapping("/register")
 public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
  User user = userService.findByUsername(userDto.getUsername());
  if (user != null) {
   model.addAttribute("Userexist", user);
   return "register";
  }
  
  userService.save(userDto);
  return "redirect:/register?success";
 }
}