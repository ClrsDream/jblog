package com.xiaoteng.blog.controller.api;

import com.xiaoteng.blog.enums.ApiErrorEnum;
import com.xiaoteng.blog.library.jwt.JwtHelper;
import com.xiaoteng.blog.model.Admin;
import com.xiaoteng.blog.service.AdminService;
import com.xiaoteng.blog.utils.HashTool;
import com.xiaoteng.blog.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController(value = "backendAuthController")
@RequestMapping("/backend/api/auth")
public class AuthController extends ApiController {
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Response login(@RequestBody Admin body) {
        log.info("backend/auth/login.username:{},password:{}", body.getUsername(), body.getPassword());
        Admin admin = adminService.findByUsername(body.getUsername());
        if (admin == null) {
            return error(ApiErrorEnum.ADMINISTRATOR_USERNAME_NOT_EXISTS);
        }
        // 比较密码
        if (!HashTool.check(body.getPassword(), admin.getPassword())) {
            return error(ApiErrorEnum.ADMINISTRATOR_PASSWORD_ERROR);
        }
        // 登录成功
        admin.setLastLoginAt(new Date());
        admin.setLastLoginIp(Helper.getIp(request));
        adminService.update(admin);

        // 返回token
        String token = jwtHelper.generateUserJwt(admin.getUsername());
        return successEmptyMes(token);
    }

}
