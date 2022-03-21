package lemonsoju_group.lemonsoju_artifact.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LectureForm {

    @NotEmpty(message = "회원 강의명 필수입니다.")
    private String lectureName;
}