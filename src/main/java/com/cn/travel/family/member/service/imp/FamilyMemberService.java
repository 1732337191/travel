package com.cn.travel.family.member.service.imp;

import com.cn.travel.cms.order.entity.Order;
import com.cn.travel.family.member.dao.FamilyMemberDao;
import com.cn.travel.family.member.entity.FamilyMember;
import com.cn.travel.family.member.service.IFamilyMemberService;
import com.cn.travel.utils.Tools;
import com.cn.travel.web.base.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyMemberService implements IFamilyMemberService {
    @Resource
    private FamilyMemberDao familyMemberDao;
    @Override
    public long count(String familyId) throws Exception {
        return familyMemberDao.count(familyId);
    }

    @Override
    public FamilyMember findById(String id) throws Exception {
        return familyMemberDao.findById(id);
    }




    @Override
    public void save(FamilyMember familyMember) throws Exception {
        familyMemberDao.save(familyMember);
    }

    @Override
    public void update(FamilyMember familyMember) throws Exception {
        familyMemberDao.update(familyMember);
    }

    @Override
    public void deleteByid(String id) throws Exception {
        familyMemberDao.deleteByid(id);
    }

    @Override
    public List<FamilyMember> findByPage(int currentPage, int pageSize, String familyId) throws Exception {
        List<FamilyMember> list = new ArrayList<FamilyMember>();
        PageHelper.startPage(currentPage, pageSize);
        if (Tools.notEmpty(familyId)) {
            list = familyMemberDao.findListByFamilyId(familyId);
        }
        PageInfo<FamilyMember> pageInfo=new PageInfo<FamilyMember>(list);
        return pageInfo.getList();
    }

    @Override
    public long state1count() throws Exception {
        return familyMemberDao.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return familyMemberDao.state2count();
    }

    //原型findById
    @Override
    public FamilyMember findByMemberIdAndFamilyId(String memberId, String familyId) throws Exception {
        return familyMemberDao.findByMemberIdAndFamilyId(memberId,familyId);
    }

    //无用
    @Override
    public FamilyMember findByFamilyId(String familyId) throws Exception {
        return familyMemberDao.findByFamilyId(familyId);
    }

    @Override
    public String findByMemberId(String memberId) {
        return familyMemberDao.findByMemberId(memberId);
    }

    public PageParam<FamilyMember> findByPageByFamilyId(int currentPage, int pageSize, String familyId)throws Exception{
        PageParam<FamilyMember> pageParam = new PageParam<>();
        Page page = PageHelper.startPage(currentPage, pageSize);
        List<FamilyMember> list = familyMemberDao.findListByFamilyId(familyId);
        pageParam.setResult(list);
        pageParam.setSize(page.getPages());
        pageParam.setCount(page.getTotal());
        pageParam.setPageNumber(currentPage);
        pageParam.setPageSize(pageSize);
        return pageParam;
    }
}
