package com.cn.travel.family.member.service;


import com.cn.travel.family.member.entity.FamilyMember;

import java.util.List;

public interface IFamilyMemberService {
    public long count(String familyId) throws Exception;

    public FamilyMember findById(String id) throws Exception;

    public void save(FamilyMember family) throws Exception;

    public void update(FamilyMember family) throws Exception;

    public void deleteByid(String id) throws Exception;

    public List<FamilyMember> findByPage(int currentPage, int pageSize, String query) throws Exception;

//    public List<Porvice> countPorvice()throws Exception;

    //    public Family login(String FamilyName, String password)throws Exception;
    public long state1count() throws Exception;

    public long state2count() throws Exception;

    //原型findById
    FamilyMember findByMemberIdAndFamilyId(String memberId, String familyId) throws Exception;
    //无用
    FamilyMember findByFamilyId(String familyId) throws Exception;

    String findByMemberId(String memberId);
}
