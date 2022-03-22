package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lemonsoju_group.lemonsoju_artifact.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    public List<Lecture> findLecturesByUserId(Long id){
        return lectureRepository.findAllByUserId(id);
    }

    public List<Lecture> findAllLectures(){
        return lectureRepository.findAll();
    }

    public Lecture findLectureById(Long id){
        return lectureRepository.findOne(id);
    }

    @Transactional
    public Long save(Lecture lecture){
        return lectureRepository.save(lecture);
    }
}


