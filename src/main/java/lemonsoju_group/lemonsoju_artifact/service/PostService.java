package lemonsoju_group.lemonsoju_artifact.service;

import lemonsoju_group.lemonsoju_artifact.domain.Lecture;
import lemonsoju_group.lemonsoju_artifact.domain.Post;
import lemonsoju_group.lemonsoju_artifact.repository.LectureRepository;
import lemonsoju_group.lemonsoju_artifact.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {


    private final PostRepository postRepository;


    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    @Transactional
    public Long save(Post post){
        return postRepository.save(post);
    }
}

