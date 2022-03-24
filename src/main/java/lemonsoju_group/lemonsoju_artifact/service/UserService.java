package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.LectureRepository;
import lemonsoju_group.lemonsoju_artifact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 클래스 자동으로 인젝션하여 생성자를 생성한다
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(User user) {

        String encodePwd = passwordEncoder.encode(user.getPwd());
        user.setPwd(encodePwd);

        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }


    /**
     * 유저 수강신청 추가
     */
    @Transactional
    public Long suGangSave(Long userId, Long lectureId) {
        Lecture lecture = lectureRepository.findOne(lectureId);
        User user = userRepository.findOne(userId);
        user.getLectures().add(lecture);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUsers = userRepository.findByUid(user.getUid());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("Already Existing User");
        }
    }


    /**
     * 회원 전체 조회
     */
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findOne(Long userId){
        return userRepository.findOne(userId);
    }


}