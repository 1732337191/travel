package com.cn.travel.family.service.imp;

import com.cn.travel.family.dao.FamilyDao;
import com.cn.travel.family.entity.Family;
import com.cn.travel.family.service.IFamilyService;
import com.cn.travel.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyService implements IFamilyService {
    @Resource
    private FamilyDao familyDao;
    @Override
    public long count() throws Exception {
        return familyDao.count();
    }

    @Override
    public Family findById(String id) throws Exception {
        return familyDao.findById(id);
    }

    @Override
    public Family findByFamilyName(String familyName) throws Exception {
        return familyDao.findByFamilyName(familyName);
    }

    @Override
    public List<Family> findList() throws Exception {
        return familyDao.findList();
    }

    @Override
    public void save(Family family) throws Exception {
        familyDao.save(family);
    }

    @Override
    public void update(Family family) throws Exception {
        familyDao.update(family);
    }

    @Override
    public void deleteByid(String id) throws Exception {
        familyDao.deleteByid(id);
    }

    @Override
    public List<Family> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Family> list = new ArrayList<Family>();
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(query)) {
            list = familyDao.findListByQuery("%" + query + "%");
        } else {
            list = familyDao.findList();
        }
        PageInfo<Family> pageInfo=new PageInfo<Family>(list);
        return pageInfo.getList();
    }

    @Override
    public long state1count() throws Exception {
        return familyDao.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return familyDao.state2count();
    }

    @Override
    public Family findByOwerId(String userId) throws Exception {
        return familyDao.findByOwerId(userId);
    }
}
