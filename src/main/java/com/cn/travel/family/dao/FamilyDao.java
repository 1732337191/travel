package com.cn.travel.family.dao;

import com.cn.travel.base.dao.BaseDao;
import com.cn.travel.family.entity.Family;
import com.cn.travel.family.provider.FamilySqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FamilyDao extends BaseDao<Family> {
    @SelectProvider(type = FamilySqlProvider.class, method = "count")
    long count();

    @SelectProvider(type = FamilySqlProvider.class, method = "findById")
    Family findById(String id);

    @SelectProvider(type = FamilySqlProvider.class, method = "findByFamilyName")
    Family findByFamilyName(String familyName);

    @SelectProvider(type = FamilySqlProvider.class, method = "findList")
    List<Family> findList();

    @SelectProvider(type = FamilySqlProvider.class, method = "findListByQuery")
    List<Family> findListByQuery(String s);

    @InsertProvider(type = FamilySqlProvider.class, method = "save")
    void save(Family family);

    @UpdateProvider(type = FamilySqlProvider.class, method = "update")
    void update(Family family);

    @UpdateProvider(type = FamilySqlProvider.class, method = "deleteByid")
    void deleteByid(String id);

    @SelectProvider(type = FamilySqlProvider.class, method = "state1count")
    long state1count();

    @SelectProvider(type = FamilySqlProvider.class, method = "state2count")
    long state2count();

    @SelectProvider(type = FamilySqlProvider.class, method = "findByOwerId")
    Family findByOwerId(String userId);
}
