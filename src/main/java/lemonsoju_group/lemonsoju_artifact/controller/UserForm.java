package lemonsoju_group.lemonsoju_artifact.controller;

import lemonsoju_group.lemonsoju_artifact.domain.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserForm {

    @NotEmpty(message = "회원 id는 필수입니다.")
    private String uid;
    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String pwd;
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private Long studentId;
    private Role role;
}