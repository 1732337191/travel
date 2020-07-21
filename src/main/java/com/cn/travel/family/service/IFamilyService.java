package com.cn.travel.family.service;


import com.cn.travel.family.entity.Family;

import java.util.List;

public interface IFamilyService {
    public long count() throws Exception;

    public Family findById(String id) throws Exception;

    public Family findByFamilyName(String familyName) throws Exception;

    public List<Family> findList() throws Exception;

    public void save(Family family) throws Exception;

    public void update(Family family) throws Exception;

    public void deleteByid(String id) throws Exception;

    public List<Family> findByPage(int currentPage, int pageSize, String query) throws Exception;

//    public List<Porvice> countPorvice()throws Exception;

    //    public Family login(String FamilyName, String password)throws Exception;
    public long state1count() throws Exception;

    public long state2count() throws Exception;

    Family findByOwerId(String userId) throws Exception;
}
