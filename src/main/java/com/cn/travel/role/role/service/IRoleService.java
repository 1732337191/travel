package com.cn.travel.role.role.service;


import com.cn.travel.role.role.entity.Role;

import java.util.List;

public interface IRoleService {
    public long count() throws Exception;

    public Role findById(String id) throws Exception;

    public Role findByRoleName(String roleName) throws Exception;

    public List<Role> findList() throws Exception;

    public void save(Role role) throws Exception;

    public void update(Role role) throws Exception;

    public void deleteByid(String id) throws Exception;

    public List<Role> findByPage(int currentPage, int pageSize, String query) throws Exception;

//    public List<Porvice> countPorvice()throws Exception;

    //    public Role login(String RoleName, String password)throws Exception;
    public long state1count() throws Exception;

    public long state2count() throws Exception;

    //启用的角色
    List<Role> findListState() throws Exception;
}
