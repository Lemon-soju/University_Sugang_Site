package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
}