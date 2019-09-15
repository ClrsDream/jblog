package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.exceptions.PostNotFoundException;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.model.PostComment;
import com.xiaoteng.blog.router.WebRouter;
import com.xiaoteng.blog.service.PostCommentService;
import com.xiaoteng.blog.service.PostService;
import com.xiaoteng.blog.service.UserService;
import com.xiaoteng.blog.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostCommentController extends BaseController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private UserService userService;

    @PostMapping(WebRouter.POST_COMMENT_CREATE)
    public RedirectView createPostComment(RedirectAttributes redirectAttributes,
                                          @PathVariable(name = "id") Long postId,
                                          @RequestParam(name = "comment_id", defaultValue = "0") Long commentId,
                                          @RequestParam(name = "content", defaultValue = "") String content) {
        content = Helper.clean(content);
        if (content.isBlank()) {
            return back("请输入评论内容", redirectAttributes);
        }
        Post post = postService.findById(postId);
        if (post == null) {
            throw new PostNotFoundException();
        }
        PostComment postComment = postCommentService.create(content, commentId, userService.getUser(), post);
        return success(prevPath(), "评论成功", redirectAttributes);
    }

}
