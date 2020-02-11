package cat.tecnocampus.webAdapter;

import cat.tecnocampus.ports.in.IUserUseCases;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by josep on 14/9/17.
 */
@Controller
public class UserWebGETController {
    private final IUserUseCases userUseCases;

    public UserWebGETController(IUserUseCases userUseCases) {
            this.userUseCases = userUseCases;
    }

    // returns a static content
    // http://localhost:8080/welcome_page.html

    //returns a dynamic content
    @GetMapping("welcome_dynamic")
    public String welcome_dynamic(Model model) {
        model.addAttribute("message", "We're the best in the whole world!!!");
        return "welcome_dynamic_page";
    }

    //get parameters from client as "request parameters"
    // http://localhost:8080//welcome_requestParam?message=Hello%20to%20everybody
    @GetMapping("welcome_requestParam")
    public String welcome_request(@RequestParam String message, Model model) {
        model.addAttribute("message", message);
        return "welcome_dynamic_page";
    }

    // get parameters from client as "path variables"
    // http://localhost:8080//welcome_pathVariable/Hello%20to%20everybody
    @GetMapping("welcome_pathVariable/{message}")
    public String welcome_pathVar(@PathVariable String message, Model model) {
        model.addAttribute("message", message);
        return "welcome_dynamic_page";
    }

    //most explicit: the preferred way
    @GetMapping("users")
    public String listUsers(Model model) {
        model.addAttribute("userLabList", userUseCases.getUsers());
        return "users";
    }

    /*
    //most implicit: hard to understand and maintain
    // the view is the same as the mapping (users.html)
    // the returned value is automatically added to model with attribute name build from the returned type: userLabList
    @GetMapping("users")
    public List<UserLab> listUsers() {
        return userUseCases.getUsers();
    }
    */

    @GetMapping("users/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("userLab", userUseCases.getUser(username));
        return "showUser";
    }
}
