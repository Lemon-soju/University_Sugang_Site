package lemonsoju_group.lemonsoju_artifact.repository;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.SuGang;
import lemonsoju_group.lemonsoju_artifact.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SuGangRepository {
    private final EntityManager em;

    public Long save(SuGang suGang){
        em.persist(suGang);
        return suGang.getId();
    }

    public List<Lecture> findLecturesById(Long id){
        return em.createQuery("select s.lecture from SuGang s where s.user.id = :userId", Lecture.class)
                .setParameter("userId", id)
                .getResultList();
    }



}