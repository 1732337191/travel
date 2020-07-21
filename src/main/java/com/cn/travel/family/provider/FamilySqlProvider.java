package com.cn.travel.family.provider;

import org.springframework.stereotype.Component;

@Component
public class FamilySqlProvider {

    public String count() {
        return "SELECT count(ID) FROM t_pz_family WHERE DELETE_STATUS=0";
    }

    public String findById() {
        return "SELECT * FROM t_pz_family WHERE ID = #{id} AND DELETE_STATUS=0";
    }

    public String findByFamilyName() {
        return "SELECT * FROM t_pz_family WHERE FAMILY_NAME = #{familyName} AND DELETE_STATUS=0";
    }

    public String findList() {
        return "SELECT * FROM t_pz_family WHERE DELETE_STATUS=0 ORDER BY ADD_TIME DESC";
    }
    //Maybe
    public String findListByQuery() {
        return "SELECT * FROM t_pz_family WHERE DELETE_STATUS=0 AND FAMILY_NAME LIKE #{query,jdbcType=VARCHAR} ORDER BY ADD_TIME DESC";
    }

    public String findByOwerId() {
        return "SELECT ID,FAMILY_NAME FROM t_pz_family WHERE DELETE_STATUS=0 AND OWER_ID=#{userId}";
    }

    public String update() {
        return "UPDATE t_pz_family SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),FAMILY_NAME=#{familyName}, " +
                "FAMILY_ADDRESS=#{familyAddress},STATE=#{state},OWER_ID=#{owerId} WHERE id=#{id}";
    }

    public String save() {
        return "INSERT INTO t_pz_family(ID,ADD_USER_ID,ADD_TIME,FAMILY_NAME,FAMILY_ADDRESS,STATE,OWER_ID) VALUES" +
                "(#{id},#{addUserId},NOW(),#{familyName},#{familyAddress},#{state},#{owerId})";
    }

    public String deleteByid() {
        return "UPDATE t_pz_family SET DELETE_STATUS=1 WHERE id=#{id}";
    }


    public String state1count() {
        return "SELECT count(ID) FROM t_pz_family WHERE STATE=1 AND DELETE_STATUS=0";
    }

    public String state2count() {
        return "SELECT count(ID) FROM t_pz_family WHERE STATE=2  AND DELETE_STATUS=0";
    }

}
