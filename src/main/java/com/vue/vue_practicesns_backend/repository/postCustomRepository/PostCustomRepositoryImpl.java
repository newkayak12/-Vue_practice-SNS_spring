package com.vue.vue_practicesns_backend.repository.postCustomRepository;

import com.vue.vue_practicesns_backend.repository.entity.post.Post;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PostCustomRepositoryImpl extends QuerydslRepositorySupport implements PostCustomRepository  {
    public PostCustomRepositoryImpl() {
        super(Post.class);
    }
}
