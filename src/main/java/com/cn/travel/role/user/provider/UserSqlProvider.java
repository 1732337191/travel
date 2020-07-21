package com.cn.travel.role.user.provider;

import org.springframework.stereotype.Component;

@Component
public class UserSqlProvider {

    public String login() {
        return "SELECT * FROM t_pz_user WHERE USER_NAME = #{userName} AND PASSWORD = #{password} AND DELETE_STATUS=0";
    }

    public String count() {
        return "SELECT count(*) FROM t_pz_user WHERE DELETE_STATUS=0";
    }

    public String findById() {
        return "SELECT * FROM t_pz_user WHERE ID = #{id} AND DELETE_STATUS=0";
//        return "SELECT u.*,r.ROLE_NAME FROM t_pz_user u LEFT JOIN t_pz_role r ON u.ROLE_ID = r.ID AND u.ID = #{id} AND u.DELETE_STATUS = 0";
    }

    public String findByUserName() {
        return "SELECT * FROM t_pz_user WHERE USER_NAME = #{userName} AND DELETE_STATUS=0";
//        return "SELECT u.*,r.ROLE_NAME FROM t_pz_user u LEFT JOIN t_pz_role r ON u.ROLE_ID = r.ID AND u.USER_NAME = #{userName} AND u.DELETE_STATUS = 0";
    }

    public String findList() {
        return "SELECT * FROM t_pz_user WHERE DELETE_STATUS=0 ORDER BY ADD_TIME DESC";
//        return "SELECT u.*,r.ROLE_NAME FROM t_pz_user u LEFT JOIN t_pz_role r ON u.ROLE_ID = r.ID AND u.DELETE_STATUS = 0 ORDER BY u.ADD_TIME DESC";
    }

    public String findListByQuery() {
        return "SELECT * FROM t_pz_user WHERE DELETE_STATUS=0 AND USER_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC";
//        return "SELECT u.*,r.ROLE_NAME FROM t_pz_user u LEFT JOIN t_pz_role r ON u.ROLE_ID = r.ID AND u.USER_NAME LIKE #{query,jdbcType=VARCHAR} AND u.DELETE_STATUS = 0 ORDER BY u.ADD_TIME DESC";

    }

    public String update() {
        return "UPDATE t_pz_user SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),ROLE_ID=#{roleId},USER_NAME=#{userName}, " +
                "PASSWORD=#{password},LINK_TEL=#{linkTel},NAME=#{name},IC_CODE=#{icCode},STATE=#{state},PROVINCE=#{province} WHERE id=#{id}";
    }

    public String save() {
        return "INSERT INTO t_pz_user(ID,ADD_USER_ID,ADD_TIME,ROLE_ID,USER_NAME,PASSWORD,LINK_TEL,NAME,IC_CODE,STATE,PROVINCE) VALUES" +
                "(#{id},#{addUserId},NOW(),#{roleId},#{userName},#{password},#{linkTel},#{name},#{icCode},#{state},#{province})";
    }

    public String deleteByid() {
        return "UPDATE t_pz_user SET DELETE_STATUS=1 WHERE id=#{id}";
    }

    public String countPorvice() {
        return "SELECT p.`id` PROVICE,COUNT(u.`PROVINCE`) COUNT FROM t_pz_province p LEFT JOIN t_pz_user u ON u.`PROVINCE` = p.`id` GROUP BY p.id;";
    }

    public String state1count() {
        return "SELECT count(*) FROM t_pz_user WHERE STATE=1 AND DELETE_STATUS=0";
    }

    public String state2count() {
        return "SELECT count(*) FROM t_pz_user WHERE STATE=2  AND DELETE_STATUS=0";
    }

//    户主家长
    public String findListOwers() {
        return "SELECT ID,USER_NAME,NAME FROM t_pz_user WHERE ROLE_ID=(SELECT ID FROM t_pz_role WHERE ROLE_NAME IN('户主','家长'))";
    }

    // 有效的户主家长
    public String findListOwerState() {
        return "SELECT ID,USER_NAME,NAME FROM t_pz_user WHERE ROLE_ID=(SELECT ID FROM t_pz_role WHERE ROLE_NAME IN('户主','家长')) AND STATE=1";
    }

    // 有效的成员findListMembers
    public String findListMembers() {
        return "SELECT ID,USER_NAME,NAME FROM t_pz_user WHERE ROLE_ID=(SELECT ID FROM t_pz_role WHERE ROLE_NAME IN('成员','家庭成员')) AND STATE=1";
    }
}
