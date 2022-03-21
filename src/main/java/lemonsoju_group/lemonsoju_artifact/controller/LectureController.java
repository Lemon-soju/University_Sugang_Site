package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
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
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/myLectures")
    public String myLectures(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {

        List<Lecture> findLectures = lectureService.findMyLectures(loginUser.getId());
        model.addAttribute("lectures", findLectures);
        return "/lectures/myLectureList";
    }

    @GetMapping("/allLectures")
    public String allLectures(Model model) {

        List<Lecture> findLectures = lectureService.findAllLectures();
        model.addAttribute("lectures", findLectures);
        return "/lectures/allLectureList";
    }

    //*************
    @GetMapping("/createLecture")
    public String createForm(Model model)
    {
        model.addAttribute("lectureForm", new LectureForm());
        return "/lectures/createLectureForm";
    }

    @PostMapping("/createLecture")
    public String create(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, @Valid LectureForm form, BindingResult result){

        if (result.hasErrors()){
            return "lectures/createLectureForm";
        }

        Lecture lecture = new Lecture();
        lecture.setLectureName(form.getLectureName());
        lecture.setUser(loginUser);
        lectureService.save(lecture);
        return "redirect:/";
    }

}