package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostMapperTest {

    private final Logger log = LoggerFactory.getLogger(PostMapperTest.class);

    @Autowired
    private PostMapper postMapper;

    @Test
    public void testPaginate() {
        List<Post> posts = postMapper.paginate("id", "desc", null);
        log.info("posts:{}", posts);
    }

}
