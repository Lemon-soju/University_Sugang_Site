package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.Post;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LoginService;
import lemonsoju_group.lemonsoju_artifact.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/addPost")
    public String createForm(Model model){
        model.addAttribute("postForm", new PostForm());
        return "/posts/createPostForm";
    }

    @PostMapping("/createPost")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, @Valid PostForm form, BindingResult result){

        if (result.hasErrors()){
            return "post/createPostForm";
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setUser(loginUser);
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/allPosts")
    public String postList(Model model)
    {
        List<Post> findPosts = postService.findAllPosts();
        model.addAttribute("posts", findPosts);
        return "/posts/allPostList";
    }
}
