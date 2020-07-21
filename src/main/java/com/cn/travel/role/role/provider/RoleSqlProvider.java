package com.cn.travel.role.role.provider;

import org.springframework.stereotype.Component;

@Component
public class RoleSqlProvider {

    public String count() {
        return "SELECT count(ID) FROM t_pz_role WHERE DELETE_STATUS=0";
    }

    public String findById() {
        return "SELECT * FROM t_pz_role WHERE ID = #{id} AND DELETE_STATUS=0";
    }

    public String findByRoleName() {
        return "SELECT * FROM t_pz_role WHERE ROLE_NAME = #{roleName} AND DELETE_STATUS=0";
    }

    public String findList() {
        return "SELECT * FROM t_pz_role WHERE DELETE_STATUS=0 ORDER BY ADD_TIME DESC";
    }

    //Maybe
    public String findListByQuery() {
        return "SELECT * FROM t_pz_role WHERE DELETE_STATUS=0 AND ROLE_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC";
    }

    public String update() {
        return "UPDATE t_pz_role SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),ROLE_NAME=#{roleName}, " +
                "STATE=#{state} WHERE id=#{id}";
    }

    public String save() {
        return "INSERT INTO t_pz_role(ID,ADD_USER_ID,ADD_TIME,ROLE_NAME,STATE) VALUES" +
                "(#{id},#{addUserId},NOW(),#{roleName},#{state})";
    }

    public String deleteByid() {
        return "UPDATE t_pz_role SET DELETE_STATUS=1 WHERE id=#{id}";
    }


    public String state1count() {
        return "SELECT count(ID) FROM t_pz_role WHERE STATE=1 AND DELETE_STATUS=0";
    }

    public String state2count() {
        return "SELECT count(ID) FROM t_pz_role WHERE STATE=2  AND DELETE_STATUS=0";
    }

    //启用的角色
    public String findListState() {
        return "SELECT * FROM t_pz_role WHERE DELETE_STATUS=0 AND STATE=1 ORDER BY ADD_TIME DESC";
    }
}
