package com.xiaoteng.blog.controller.api;

import com.xiaoteng.blog.exceptions.MemberNotFoundException;
import com.xiaoteng.blog.model.User;
import com.xiaoteng.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "backendMemberController")
@RequestMapping("/backend/api/member")
public class MemberController extends ApiController {

    private final static Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public Response members(@RequestBody(required = false) User user,
                            @RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "page_size", defaultValue = "20") int pageSize) {
        log.info("userQuery:{}", user);
        page = page <= 0 ? 1 : page;
        int start = (page - 1) * pageSize;
        List<User> users = userService.paginate(user, start, pageSize);
        Long count = userService.count(user);
        Paginator paginator = new Paginator(page, pageSize, count, users);
        return successEmptyMes(paginator);
    }

    @GetMapping("/{id:\\d+}")
    public Response detail(@PathVariable(name = "id") Long userId) throws MemberNotFoundException {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new MemberNotFoundException();
        }
        return successEmptyMes(user);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable(name = "id") Long userId,
                           @RequestBody User user) {
        userService.update(user, userId);
        return successEmptyMes(null);
    }

}
