package com.cn.travel.role.role.dao;

import com.cn.travel.base.dao.BaseDao;
import com.cn.travel.role.role.entity.Role;
import com.cn.travel.role.role.provider.RoleSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoleDao extends BaseDao<Role> {
    @SelectProvider(type = RoleSqlProvider.class, method = "count")
    long count();

    @SelectProvider(type = RoleSqlProvider.class, method = "findById")
    Role findById(String id);

    @SelectProvider(type = RoleSqlProvider.class, method = "findByRoleName")
    Role findByRoleName(String roleName);

    @SelectProvider(type = RoleSqlProvider.class, method = "findList")
    List<Role> findList();

    @SelectProvider(type = RoleSqlProvider.class, method = "findListByQuery")
    List<Role> findListByQuery(String s);

    @InsertProvider(type = RoleSqlProvider.class, method = "save")
    void save(Role role);

    @UpdateProvider(type = RoleSqlProvider.class, method = "update")
    void update(Role role);

    @UpdateProvider(type = RoleSqlProvider.class, method = "deleteByid")
    void deleteByid(String id);

    @SelectProvider(type = RoleSqlProvider.class, method = "state1count")
    long state1count();

    @SelectProvider(type = RoleSqlProvider.class, method = "state2count")
    long state2count();

    //启用的角色
    @SelectProvider(type = RoleSqlProvider.class, method = "findListState")
    List<Role> findListState();
}
