package com.cn.travel.web.manager;

import com.cn.travel.family.entity.Family;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class FamilyController extends BaseController {

    @Autowired
    FamilyService familyService;
    @Autowired
    UserService userService;

    @RequestMapping("/familyList")
    public ModelAndView familyList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        ModelAndView mv = this.getModeAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = familyService.count();
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
        List<Family> list = familyService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
        List<User> owers = userService.findListOwers();
        mv.addObject("owers", owers);
        mv.addObject("pageData", list);
        if (Tools.notEmpty(query)) {
            mv.addObject("query", query);
            pageParam.setCount(list.size());
            if (list.size() > pageParam.getPageSize()) {
                pageParam.setSize(list.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }
        }
        mv.addObject("pageParam", pageParam);
        mv.setViewName("family/allfamilies");//页面
        return mv;
    }


    @RequestMapping("/familyAdd")
    public ModelAndView familyAdd() throws Exception {
        ModelAndView mv = this.getModeAndView();
        List<User> owerState = userService.findListOwerState();
        mv.addObject("owerState", owerState);
        mv.addObject("entity", new Family());
        mv.setViewName("family/familyEdit");
        return mv;
    }

//    @RequestMapping("/selectAddress")
//    public ModelAndView selectAddress() throws Exception {
//        ModelAndView mv = this.getModeAndView();
//        mv.setViewName("family/selectAddress");
//        return mv;
//    }

    @RequestMapping("/familyView")
    public ModelAndView familyView(String id) throws Exception {
        ModelAndView mv = this.getModeAndView();
        List<User> owerState = userService.findListOwerState();
        mv.addObject("owerState", owerState);
        try {
            mv.addObject("entity", familyService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("family/familyView");
        return mv;
    }

    @RequestMapping("/familyEdit")
    public ModelAndView familyEdit(String id) throws Exception {
        ModelAndView mv = this.getModeAndView();
        List<User> owerState = userService.findListOwerState();
        mv.addObject("owerState", owerState);
        try {
            mv.addObject("entity", familyService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("family/familyEdit");
        return mv;
    }

    @RequestMapping("/familySave")
    public ModelAndView familySave(HttpServletRequest request, String id, RedirectAttributes redirectAttributes) throws Exception {
        Admin temAdmin = (Admin) request.getSession().getAttribute("admin");
        ModelAndView mv = this.getModeAndView();
        Family entity = null;
        try {
            if (Tools.notEmpty(id)) {
                entity = familyService.findById(id);
            } else {
                entity = new Family();
            }
            this.bindValidateRequestEntity(request, entity);
            if (Tools.isEmpty(entity.getId())) {
                Family object = familyService.findByFamilyName(entity.getFamilyName());
                if (object != null) {
                    mv.addObject("message", "家庭名已存在!");
                    mv.addObject("entity", entity);
                    mv.setViewName("family/familyEdit");
                    return mv;
                }
                entity.setId(Tools.getUUID());//生成主键
                entity.setAddUserId(temAdmin.getId());
                familyService.save(entity);
            } else {
                entity.setModifyUserId(temAdmin.getId());
                familyService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageData", familyService.findByPage(1, 10, null));
        PageParam pageParam = new PageParam();
        long count = 0;
        try {
            count = familyService.count();
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
        List<User> owers = userService.findListOwers();
        mv.addObject("owers", owers);
        mv.addObject("pageParam", pageParam);
        mv.setViewName("family/allfamilies");
        return mv;
    }

    @RequestMapping("/familyDelete")
    public String familyDelete(String id) {
        if (Tools.notEmpty(id)) {
            try {
                familyService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return REDIRECT + "/manager/familyList";
    }
}
