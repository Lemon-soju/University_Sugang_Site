package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepository {

    private final EntityManager em;

    public Long save(Lecture lecture) {
        em.persist(lecture);
        return lecture.getId();
    }

    public Lecture findOne(Long id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select l from Lecture l",Lecture.class).getResultList();
    }

    public List<Lecture> findAllByUserId(Long id){
        return em.createQuery("select l from Lecture l where l.user.id = :userId", Lecture.class)
                .setParameter("userId", id)
                .getResultList();
    }
}