package com.cn.travel.web.manager;

import com.cn.travel.family.entity.Family;
import com.cn.travel.family.member.entity.FamilyMember;
import com.cn.travel.family.member.service.imp.FamilyMemberService;
import com.cn.travel.family.service.imp.FamilyService;
import com.cn.travel.role.admin.entity.Admin;
import com.cn.travel.role.user.entity.User;
import com.cn.travel.role.user.service.imp.UserService;
import com.cn.travel.utils.Tools;
import com.cn.travel.web.base.BaseController;
import com.cn.travel.web.base.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class FamilyMemberController extends BaseController {

    @Autowired
    FamilyMemberService familyMemberService;
    @Autowired
    UserService userService;

    @RequestMapping("/familyMemberList")
    public ModelAndView familyMemberList(PageParam pageParam, @RequestParam(value = "familyId", required = true) String familyId) throws Exception {
        ModelAndView mv = this.getModeAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = familyMemberService.count(familyId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if (count <= 10) {
                pageParam.setSize(1);
            } else {
                pageParam.setSize(count % 10 == 0 ? count / 10 : count / 10 + 1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        List<FamilyMember> list = familyMemberService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), familyId);
        List<User> members = userService.findListMembers();
        mv.addObject("members", members);
        mv.addObject("pageData", list);
        mv.addObject("familyId", familyId);//list may null
        if (Tools.notEmpty(familyId)) {
            pageParam.setCount(list.size());
            if (list.size() > pageParam.getPageSize()) {
                pageParam.setSize(list.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }
        }
        mv.addObject("pageParam", pageParam);
        mv.setViewName("family/member/allMembers");//页面
        return mv;
    }


    @RequestMapping("/familyMemberAdd")
    public ModelAndView familyMemberAdd(@RequestParam(value = "familyId", required = true) String familyId) throws Exception {
        ModelAndView mv = this.getModeAndView();
        List<User> members = userService.findListMembers();
        mv.addObject("members", members);
        mv.addObject("familyId", familyId);
        mv.addObject("entity", new FamilyMember());
        mv.setViewName("family/member/memberEdit");
        return mv;
    }

    @RequestMapping("/familyMemberSave")
    public ModelAndView familySave(HttpServletRequest request, FamilyMember entity,RedirectAttributes redirectAttributes) throws Exception {
        Admin temAdmin = (Admin) request.getSession().getAttribute("admin");
        ModelAndView mv = this.getModeAndView();
            this.bindValidateRequestEntity(request, entity);

            if (Tools.isEmpty(entity.getId())) {//maybe findByMemberIdAndFamilyId(memberId,familyId);
                FamilyMember object = familyMemberService.findByMemberIdAndFamilyId(entity.getMemberId(),entity.getFamilyId());
                if (object != null) {
                    mv.addObject("message", "该成员已加入!");
                    mv.addObject("entity", entity);
                    List<User> members = userService.findListMembers();
                    mv.addObject("members", members);
                    mv.addObject("familyId", entity.getFamilyId());
                    mv.setViewName("family/member/memberEdit");
                    return mv;
                }
                entity.setId(Tools.getUUID());//生成主键
                entity.setAddUserId(temAdmin.getId());
                familyMemberService.save(entity);
            }
//            else {
//                entity.setModifyUserId(temAdmin.getId());
//                familyMemberService.update(entity);
//            }

        mv.addObject("pageData", familyMemberService.findByPage(1, 10, null));
        PageParam pageParam = new PageParam();
        long count = 0;
        try {
            count = familyMemberService.count(entity.getFamilyId());//familyId
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageParam.setCount(count);
        if (count <= 10) {
            pageParam.setSize(1);
        } else {
            pageParam.setSize(count % 10 == 0 ? count / 10 : count / 10 + 1);
        }
        pageParam.setPageNumber(1);
        pageParam.setPageSize(10);
        List<User> members = userService.findListMembers();
        mv.addObject("members", members);
        mv.addObject("pageParam", pageParam);
        mv.addObject("familyId", entity.getFamilyId());
        mv.setViewName("family/member/allMembers");
        return mv;
    }

    @RequestMapping("/familyMemberDelete")
    public String familyDelete(String id ,String familyId) {
        if (Tools.notEmpty(id)) {
            try {
                familyMemberService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/familyMemberList?familyId="+familyId;
    }
}
