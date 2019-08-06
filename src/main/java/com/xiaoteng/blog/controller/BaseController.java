package com.xiaoteng.blog.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Map;

public class BaseController {

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
        redirectView.setUrl(url);
        redirectAttributes.addFlashAttribute(flag, message);
        return redirectView;
    }

}
