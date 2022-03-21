package lemonsoju_group.lemonsoju_artifact.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostForm {

    @NotEmpty(message = "게시물 제목은 필수입니다.")
    private String title;
    @NotEmpty(message = "게시물 내용은 필수입니다.")
    private String content;
}
