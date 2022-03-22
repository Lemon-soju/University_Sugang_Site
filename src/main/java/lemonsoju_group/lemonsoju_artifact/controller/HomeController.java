package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
import lemonsoju_group.lemonsoju_artifact.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final LectureService lectureService;

    @GetMapping("/home")
    public String main(Model model)
    {
        return "home";
    }


    /**
     * 수강신청
     */
    @GetMapping("/suGang")
    public String list(Model model){
        List<Lecture> findLectures = lectureService.findAllLectures();
        model.addAttribute("lectures", findLectures);
        return "suGangList";
    }

    @PostMapping("/suGang/{lectureId}/add")
    public String suGang(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, @PathVariable("lectureId") Long lectureId)
    {
        Lecture lecture = lectureService.findLectureById(lectureId);

        User user = loginUser;
        user.getLectures().add(lecture);
        //*****************************************

        return "suGangList";
    }

}