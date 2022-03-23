package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.Post;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
import lemonsoju_group.lemonsoju_artifact.service.LoginService;
import lemonsoju_group.lemonsoju_artifact.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final LectureService lectureService;

    @GetMapping("/posts/addPost/{lectureId}")
    public String createForm(@PathVariable("lectureId") Long lectureId, Model model){
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("lectureId", lectureId);
        return "/posts/createPostForm";
    }

    @PostMapping("/createPost")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, @Valid PostForm form, @RequestParam("lectureId") Long lectureId, BindingResult result){

        if (result.hasErrors()){
            return "/posts/createPostForm";
        }

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setUser(loginUser);
        Lecture lecture = lectureService.findLectureById(lectureId);
        post.setLecture(lecture);
        postService.save(post);
        return "redirect:/myLectures";
    }

    @GetMapping("/allPosts")
    public String postList(Model model)
    {
        List<Post> findPosts = postService.findAllPosts();
        model.addAttribute("posts", findPosts);
        return "/posts/allPostList";
    }

    @GetMapping("/posts/{id}")
    public String detail(@PathVariable("id") Long lectureId, Model model)
    {
        Post post = postService.findPostById(lectureId);
        model.addAttribute("post", post);
        return "/posts/detail";
    }
}
