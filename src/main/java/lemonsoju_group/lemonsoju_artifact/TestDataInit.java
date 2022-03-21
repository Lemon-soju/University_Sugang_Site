package lemonsoju_group.lemonsoju_artifact;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.Post;
import lemonsoju_group.lemonsoju_artifact.domain.Role;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.service.LectureService;
import lemonsoju_group.lemonsoju_artifact.service.PostService;
import lemonsoju_group.lemonsoju_artifact.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserService userService;
    private final LectureService lectureService;
    private final PostService postService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        /**
         * 테스트 학생 유저 추가
         */
        User userA = new User();
        userA.setUid("lemon");
        userA.setPwd("asd123");
        userA.setName("lemon_soju");
        userA.setStudentId(2017320162L);
        userA.setRole(Role.STUDENT);
        userService.join(userA);

        /**
         * 테스트 교수 유저 추가
         */
        User userB = new User();
        userB.setUid("lemon2");
        userB.setPwd("asd123");
        userB.setName("James");
        userB.setStudentId(2018613142L);
        userB.setRole(Role.PROFESSOR);
        userService.join(userB);

        User userC = new User();
        userC.setUid("lemon3");
        userC.setPwd("asd123");
        userC.setName("Charlie");
        userC.setStudentId(2014326122L);
        userC.setRole(Role.PROFESSOR);
        userService.join(userC);

        /**
         * 테스트 강의 추가
         */
        Lecture lectureA = new Lecture();
        lectureA.setLectureName("JAVA Programming");
        lectureA.setUser(userB);
        lectureService.save(lectureA);

        Lecture lectureB = new Lecture();
        lectureB.setLectureName("PYTHON Programming");
        lectureB.setUser(userC);
        lectureService.save(lectureB);

        /**
         * 테스트 강의게시물 추가
         */
        Post postA = new Post();
        postA.setTitle("test01");
        postA.setContent("Hello~ This course is Java Programming!!");
        postA.setUser(userB);
        postA.setLecture(lectureA);
        postService.save(postA);

    }
}