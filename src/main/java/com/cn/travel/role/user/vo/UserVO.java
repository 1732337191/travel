package com.cn.travel.role.user.vo;

import com.cn.travel.role.role.entity.Role;
import com.cn.travel.role.user.entity.User;

public class UserVO extends User {
    private Role role;

    public Role getRole() {
        return role;
    }
    //getRoleName
//    public String getRoleName() {
//        return role.getRoleName();
//    }
    public void setRole(Role role) {
        this.role = role;
    }
}
