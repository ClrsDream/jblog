package com.xiaoteng.blog.mappers;

import com.xiaoteng.blog.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    Long insert(Post post);

    Post findById(Long id);

    void readNumInc(Long id);

    List<Post> paginate(@Param("sort") String sort, @Param("direct") String direct, @Param("userId") Long userId);

    Long count(@Param("userId") Long userId);

    Long userFavCount(@Param("postId") Long postId);

    void tagRelationCreate(Long postId, Long tagId);

    List<Post> paginateUserFav(Long userId);

    void userFavCountDec(Long postId);

    void userFavCountInc(Long postId);

}
