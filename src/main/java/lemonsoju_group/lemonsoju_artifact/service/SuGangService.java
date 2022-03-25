package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.SuGang;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.SuGangRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 클래스 자동으로 인젝션하여 생성자를 생성한다
@Slf4j
public class SuGangService {

    private final SuGangRepository suGangRepository;


    /**
     * 유저 수강신청 추가
     */
    @Transactional
    public Long suGangSave(SuGang suGang) {
        validateDuplicateUser(suGang);
        return suGangRepository.save(suGang);
    }

    // 중복으로 수강신청을 못하도록 체크
    private void validateDuplicateUser(SuGang sugang) {
        List<SuGang> findSuGangs = suGangRepository.findSuGang(sugang.getUser(), sugang.getLecture());

        if (!findSuGangs.isEmpty()) {
            throw new IllegalStateException("Already Existing SuGang");
        }
    }



    /**
     * 수강신청한 과목 조회
     */
    public List<Lecture> findLecturesById(Long id){
        return suGangRepository.findLecturesById(id);
    }

}
