package com.cn.travel.role.user.dao;

import com.cn.travel.base.dao.BaseDao;
import com.cn.travel.role.role.entity.Role;
import com.cn.travel.role.user.provider.UserSqlProvider;
import com.cn.travel.role.user.vo.UserVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserVODao extends BaseDao<UserVO> {

//    @Results(id="VuserMap", value={
//            @Result(column="ID", property="id"),
//            @Result(column="ADD_USER_ID", property="addUserId"),
//            @Result(column="ADD_TIME ", property="addTime",jdbcType= JdbcType.TIMESTAMP),
//            @Result(column="DELETE_STATUS", property="deleteStatus"),
//            @Result(column="MODIFY_USER_ID", property="modifyUserId"),
//            @Result(column="MODIFY_TIME", property="modifyTime", jdbcType= JdbcType.TIMESTAMP),
////            @Result(column="ROLE_ID", property="role", javaType= Role.class,one=@One(select="com.cn.travel.role.role.dao.RoleDao.findById")),
//            @Result(column="ROLE_ID", property="role", javaType= Role.class),
//            @Result(column="USER_NAME ", property="userName"),
//            @Result(column="PASSWORD ", property="password"),
//            @Result(column="LINK_TEL", property="linkTel"),
//            @Result(column="NAME", property="name"),
//            @Result(column="IC_CODE", property="icCode"),
//            @Result(column="STATE", property="state"),
//            @Result(column="PROVINCE", property="province")
//    })
//
//    @SelectProvider(type = UserSqlProvider.class, method = "findById")
//    @ResultMap("VuserMap")
//    public UserVO findById(@Param("id") String id);
//
//    @SelectProvider(type = UserSqlProvider.class, method = "findByUserName")
//    @ResultMap("VuserMap")
//    public UserVO findByUserName(@Param("userName") String userName);
//
//    @SelectProvider(type = UserSqlProvider.class, method = "findList")
//    @ResultMap("VuserMap")
//    public List<UserVO> findList();
//
//    @SelectProvider(type = UserSqlProvider.class, method = "findListByQuery")
//    @ResultMap("VuserMap")
//    public List<UserVO> findListByQuery(@Param("query") String query);
}
