package com.xiaoteng.blog.service;

import com.xiaoteng.blog.mappers.AdminMapper;
import com.xiaoteng.blog.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void insert(Admin admin) {
        adminMapper.insert(admin);
    }

    public void delete(Long id) {
        adminMapper.delete(id);
    }

    public void update(Admin admin) {
        adminMapper.update(admin);
    }

    public List<Admin> all() {
        return adminMapper.all();
    }

    public Admin find(Long id) {
        return adminMapper.find(id);
    }

    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }

}
