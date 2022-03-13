package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRepository {

    private final EntityManager em;

    public Long save(Lecture lecture) {
        em.persist(lecture);
        return lecture.getId();
    }

    public Lecture findOne(Long id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select i from Lecture l",Lecture.class).getResultList();
    }

    public List<Lecture> findByUidAll(String uid){
        return em.createQuery("select u from Lecture l where l.uid = :uid", Lecture.class)
                .setParameter("uid", uid)
                .getResultList();
    }
}