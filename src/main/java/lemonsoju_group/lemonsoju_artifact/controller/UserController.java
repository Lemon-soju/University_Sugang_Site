package lemonsoju_group.lemonsoju_artifact.controller;


import lemonsoju_group.lemonsoju_artifact.domain.Role;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/new")
    public String createForm(Model model){
        model.addAttribute("userForm", new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(@Valid UserForm form, BindingResult result){

        if (result.hasErrors()){
            return "users/createUserForm";
        }

        User user = new User();
        user.setUid(form.getUid());
        user.setPwd(form.getPwd());
        user.setName(form.getName());
        user.setStudentId(form.getStudentId());
        user.setRole(form.getRole());
        userService.join(user);
        return "redirect:/";
    }
}