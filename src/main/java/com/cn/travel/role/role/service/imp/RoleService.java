package com.cn.travel.role.role.service.imp;

import com.cn.travel.role.role.dao.RoleDao;
import com.cn.travel.role.role.entity.Role;
import com.cn.travel.role.role.service.IRoleService;
import com.cn.travel.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Resource
    private RoleDao roleDao;
    @Override
    public long count() throws Exception {
        return roleDao.count();
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public Role findByRoleName(String roleName) throws Exception {
        return roleDao.findByRoleName(roleName);
    }

    @Override
    public List<Role> findList() throws Exception {
        return roleDao.findList();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) throws Exception {
        roleDao.update(role);
    }

    @Override
    public void deleteByid(String id) throws Exception {
        roleDao.deleteByid(id);
    }

    @Override
    public List<Role> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Role> list = new ArrayList<Role>();
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = roleDao.findListByQuery("%" + query + "%");
        } else {
            list = roleDao.findList();
        }
        PageInfo<Role> pageInfo=new PageInfo<Role>(list);
        return pageInfo.getList();
    }

    @Override
    public long state1count() throws Exception {
        return roleDao.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return roleDao.state2count();
    }

    //启用的角色
    @Override
    public List<Role> findListState() throws Exception {
        return roleDao.findListState();
    }
}
