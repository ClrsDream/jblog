package com.xiaoteng.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    protected RedirectView back(String errorMessage, RedirectAttributes redirectAttribute) {
        String query = request.getQueryString();
        String url = request.getRequestURI();
        if (query != null && !query.isEmpty()) {
            url = request.getRequestURI() + "?" + query;
        }
        return flashState("error", url, errorMessage, redirectAttribute);
    }

    // 失败的跳转
    protected RedirectView error(String url, String errorMessage, RedirectAttributes redirectAttributes) {
        return flashState("error", url, errorMessage, redirectAttributes);
    }

    // 成功的跳转
    protected RedirectView success(String url, String successMessage, RedirectAttributes redirectAttributes) {
        return flashState("success", url, successMessage, redirectAttributes);
    }

    private RedirectView flashState(String flag, String url, String message, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        // 跳转到url
        redirectView.setUrl(url);
        // flash提示信息
        redirectAttributes.addFlashAttribute(flag, message);
        return redirectView;
    }

}
