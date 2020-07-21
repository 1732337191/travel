package com.cn.travel.family.member.provider;

import org.springframework.stereotype.Component;

@Component
public class FamilyMemberSqlProvider {

    public String count() {
        return "SELECT count(ID) FROM t_pz_family_member WHERE DELETE_STATUS=0 AND FAMILY_ID = #{familyId}";
    }

    public String findById() {
        return "SELECT * FROM t_pz_family_member WHERE ID = #{id} AND DELETE_STATUS=0";
    }

    public String findByFamilyName() {
        return "SELECT * FROM t_pz_family_member WHERE FAMILY_NAME = #{familyName} AND DELETE_STATUS=0";
    }

    public String findList() {
        return "SELECT * FROM t_pz_family_member WHERE DELETE_STATUS=0 ORDER BY ADD_TIME DESC";
    }

    public String findListByFamilyId() {
        return "SELECT * FROM t_pz_family_member WHERE DELETE_STATUS=0 AND FAMILY_ID = #{familyId} ORDER BY ADD_TIME DESC";
    }

    public String update() {
        return "UPDATE t_pz_family_member SET MODIFY_USER_ID=#{modifyUserId},MODIFY_TIME=NOW(),FAMILY_NAME=#{familyName}, " +
                "FAMILY_ADDRESS=#{familyAddress},STATE=#{state},OWER_ID=#{owerId} WHERE id=#{id}";
    }

    public String save() {
        return "INSERT INTO t_pz_family_member(ID,ADD_USER_ID,ADD_TIME,MEMBER_ID,FAMILY_ID) VALUES" +
                "(#{id},#{addUserId},NOW(),#{memberId},#{familyId})";
    }

    public String deleteByid() {
        return "UPDATE t_pz_family_member SET DELETE_STATUS=1 WHERE id=#{id}";
    }


    public String state1count() {
        return "SELECT count(ID) FROM t_pz_family WHERE STATE=1 AND DELETE_STATUS=0";
    }

    public String state2count() {
        return "SELECT count(ID) FROM t_pz_family WHERE STATE=2  AND DELETE_STATUS=0";
    }

    //原型findById
    public String findByMemberIdAndFamilyId() {
        return "SELECT * FROM t_pz_family_member WHERE MEMBER_ID = #{memberId} AND FAMILY_ID = #{familyId} AND DELETE_STATUS=0";
    }

    public String findByFamilyId() {
        return "SELECT * FROM t_pz_family_member WHERE FAMILY_ID = #{familyId} AND DELETE_STATUS=0";
    }

    public String findByMemberId() {
        return "SELECT FAMILY_ID FROM t_pz_family_member WHERE MEMBER_ID = #{memberId} AND DELETE_STATUS=0";
    }
}