package cat.tecnocampus.webAdapter;

import cat.tecnocampus.domain.NoteLab;
import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.service.UserUseCases;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CreateUserWebController {
    private UserUseCases userUseCases;

    public CreateUserWebController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping("createUser")
    public String createUser(Model model) {
        model.addAttribute(new UserLab());
        return "userForm";
    }

    @PostMapping("createUser")
    //See Errors error parameters: it takes the errors from the validators. It MUST be right after the parameters
    public String createUser(@Valid UserLab userLab, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "userForm";
        }

        model.addAttribute("username", userLab.getUsername());

        //register user in domain classes
        userUseCases.registerUser(userLab);

        redirectAttributes.addAttribute("username", userLab.getUsername());

        //return "redirect:users/" + user.getUsername(); //this is dangerous because username can contain a dangerous string (like sql injection)

        return "redirect:/users/{username}";
    }

    @GetMapping("/users/{user}/createNote")
    public String getCreateNote(@PathVariable String user, Model model) {

        model.addAttribute("noteLab", new NoteLab());
        model.addAttribute("user", user);

        return "noteForm";
    }

    @PostMapping("/users/{user}/createNote")
    public String postCreateNote(@Valid NoteLab noteLab, Errors errors, @PathVariable String user, Model model) {

        if (errors.hasErrors()) {
            return "noteForm";
        }

        userUseCases.createUserNote(userUseCases.getUser(user), noteLab);

        return "redirect:/users/{user}";
    }

}
