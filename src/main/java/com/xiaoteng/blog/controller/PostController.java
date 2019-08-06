package com.xiaoteng.blog.controller;

import com.xiaoteng.blog.exceptions.PostNotFoundException;
import com.xiaoteng.blog.model.Post;
import com.xiaoteng.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    @PostMapping("/create")
    public RedirectView store(RedirectAttributes redirectAttributes,
                              @Valid @ModelAttribute Post post,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.error("/post/create", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), redirectAttributes);
        }
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setPublishedAt(post.getPublishedAt());
        postRepository.save(newPost);

        return super.success("/", "添加成功", redirectAttributes);
    }

    @GetMapping("/{id}")
    public String detail(ModelMap modelMap,
                         @PathVariable("id") Long id) {
       Optional<Post> optional = postRepository.findById(id);
       if (!optional.isPresent()) {
            throw new PostNotFoundException();
       }
       Post post = optional.get();
       modelMap.addAttribute("post", post);
       return "/post/detail";
    }

}
