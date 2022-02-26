package com.vue.vue_practicesns_backend.repository.postCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.vue.vue_practicesns_backend.repository.entity.post.QPost.post;

@Slf4j
public class PostCustomRepositoryImpl extends QuerydslRepositorySupport implements PostCustomRepository  {
    public PostCustomRepositoryImpl() {
        super(Post.class);
    }
    @Override
    public List<Post> fetchPosts(Long userNo, Pageable pageable) {
        log.warn("offset {}", pageable.getOffset());
        log.warn("pageNumber {}", pageable.getPageNumber());
        log.warn("getPageSize {}", pageable.getPageSize());
        log.warn("first {}", pageable.first());
        log.warn("next {}", pageable.next());
        return from(post)
               .where(post.user.userNo.eq(userNo))
               .orderBy(post.createdDate.desc())
               .offset(pageable.getOffset())
               .limit(pageable.getPageSize())
               .fetch();
    }
}
