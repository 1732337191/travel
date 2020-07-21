package com.cn.travel.web.manager;

import com.cn.travel.role.admin.entity.Admin;
import com.cn.travel.role.role.entity.Role;
import com.cn.travel.role.role.service.imp.RoleService;
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
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/roleList")
    public ModelAndView roleList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) throws Exception {
        ModelAndView mv = this.getModeAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = roleService.count();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if(count<=10){
                pageParam.setSize(1);
            }else{
                pageParam.setSize(count%10==0?count/10:count/10+1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        List<Role> list = roleService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
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
        mv.addObject("pageParam",pageParam);
        mv.setViewName("role/allRoles");//页面
        return mv;
    }


    @RequestMapping("/roleAdd")
    public ModelAndView roleAdd(){
        ModelAndView mv = this.getModeAndView();
        mv.addObject("entity",new Role());
        mv.setViewName("role/roleEdit");
        return mv;
    }

    @RequestMapping("/roleView")
    public ModelAndView roleView(String id){
        ModelAndView mv = this.getModeAndView();
        try {
            mv.addObject("entity",roleService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("role/roleView");
        return mv;
    }

    @RequestMapping("/roleEdit")
    public ModelAndView roleEdit(String id){
        ModelAndView mv = this.getModeAndView();
        try {
            mv.addObject("entity",roleService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("role/roleEdit");
        return mv;
    }

    @RequestMapping("/roleSave")
    public ModelAndView roleSave(HttpServletRequest request, String id, RedirectAttributes redirectAttributes) throws Exception {
        Admin temAdmin = (Admin) request.getSession().getAttribute("admin");
        ModelAndView mv = this.getModeAndView();
        Role entity = null;
        try {
            if(Tools.notEmpty(id)){
                entity = roleService.findById(id);
            }else{
                entity = new Role();
            }
            this.bindValidateRequestEntity(request,entity);
            if (Tools.isEmpty(entity.getId())){
                Role object = roleService.findByRoleName(entity.getRoleName());
                if (object != null) {
                    mv.addObject("message","角色名已存在!");
                    mv.addObject("entity",entity);
                    mv.setViewName("role/roleEdit");
                    return mv;
                }
                entity.setId(Tools.getUUID());//生成主键
                entity.setAddUserId(temAdmin.getId());
                roleService.save(entity);
            }else{
                entity.setModifyUserId(temAdmin.getId());
                roleService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageData", roleService.findByPage(1, 10,null));
        PageParam pageParam =new PageParam();
        long count = 0;
        try {
            count = roleService.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageParam.setCount(count);
        if(count<=10){
            pageParam.setSize(1);
        }else{
            pageParam.setSize(count%10==0?count/10:count/10+1);
        }
        pageParam.setPageNumber(1);
        pageParam.setPageSize(10);
        mv.addObject("pageParam",pageParam);
        mv.setViewName("role/allRoles");
        return mv;
    }

    @RequestMapping("/roleDelete")
    public String roleDelete(String id){
        if(Tools.notEmpty(id)){
            try {
                roleService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return REDIRECT+"/manager/roleList";
    }
}
