package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.SessionConst;
import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.SuGang;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
import lemonsoju_group.lemonsoju_artifact.service.SuGangService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class SuGangController {


    private final LectureService lectureService;
    private final UserService userService;
    private final SuGangService suGangService;


    /**
     * 수강신청
     */
    @GetMapping("/suGang")
    public String list(Model model){
        List<Lecture> findLectures = lectureService.findAllLectures();
        model.addAttribute("lectures", findLectures);
        return "/suGangs/suGangRegister";
    }

    @GetMapping("/suGang/{lectureId}/add")
    public String suGang(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, @PathVariable("lectureId") Long lectureId)
    {
        SuGang suGang = new SuGang();
        Lecture lecture = lectureService.findLectureById(lectureId);

        suGang.setUser(loginUser);
        suGang.setLecture(lecture);
        suGangService.suGangSave(suGang);


        List<Lecture> lectures = suGangService.findLecturesById(loginUser.getId());

        model.addAttribute("lectures", lectures);
        return "/suGangs/mySuGangList";
    }

    @GetMapping("/mySuGang")
    public String suGang(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser)
    {
        Long loginUserId = loginUser.getId();
        log.info("!!!!");
        List<Lecture> lectures = suGangService.findLecturesById(loginUserId);
        model.addAttribute("lectures", lectures);
        log.info("@@@@");
        return "/suGangs/mySuGangList";
    }
}
