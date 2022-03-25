package lemonsoju_group.lemonsoju_artifact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String uid;
    private String pwd;
    private String name;
    private Long studentId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Lecture> lectures;

    //== 연관관계 메서드 ==//
    public void addLecture(Lecture lecture){
        lectures.add(lecture); // OrderItem를 Order에 저장
        lecture.setUser(this); // Order를 orderItem에 저장
    }
}