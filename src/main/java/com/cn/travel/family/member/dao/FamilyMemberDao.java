package com.cn.travel.family.member.dao;

import com.cn.travel.base.dao.BaseDao;
import com.cn.travel.family.member.entity.FamilyMember;
import com.cn.travel.family.member.provider.FamilyMemberSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FamilyMemberDao extends BaseDao<FamilyMember> {
    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "count")
    long count(String familyId);

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findById")
    FamilyMember findById(String id);

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findByFamilyName")
    FamilyMember findByFamilyName(String familyName);

//    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findList")
//    List<FamilyMember> findList();

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findListByFamilyId")
    List<FamilyMember> findListByFamilyId(String familyId);

    @InsertProvider(type = FamilyMemberSqlProvider.class, method = "save")
    void save(FamilyMember familyMember);

    @UpdateProvider(type = FamilyMemberSqlProvider.class, method = "update")
    void update(FamilyMember family);

    @UpdateProvider(type = FamilyMemberSqlProvider.class, method = "deleteByid")
    void deleteByid(String id);

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "state1count")
    long state1count();

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "state2count")
    long state2count();

    //原型findById, @Param必加，Mybatis的参数匹配机制
    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findByMemberIdAndFamilyId")
    FamilyMember findByMemberIdAndFamilyId(@Param("memberId") String memberId, @Param("familyId") String familyId);

    //无用
    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findByFamilyId")
    FamilyMember findByFamilyId(@Param("familyId") String familyId);

    @SelectProvider(type = FamilyMemberSqlProvider.class, method = "findByMemberId")
    String findByMemberId(@Param("memberId") String memberId);
}
