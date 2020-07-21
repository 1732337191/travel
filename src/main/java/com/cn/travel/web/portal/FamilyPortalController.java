package com.cn.travel.web.portal;

import com.cn.travel.cms.order.entity.Order;
import com.cn.travel.cms.order.service.imp.OrderService;
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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FamilyPortalController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    FamilyService familyService;
    @Autowired
    FamilyMemberService familyMemberService;

    @RequestMapping("/myFamily")
    public ModelAndView myOrder(HttpSession httpSession,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize
    ) throws Exception {
        ModelAndView mv = this.getModeAndView();
        if (httpSession.getAttribute("roleId").equals("797e8071ac174de6ab01745273bb28a7")) {
            //户主
            Family familyIdAndName = familyService.findByOwerId((String) httpSession.getAttribute("userId"));

            if (familyIdAndName == null) {
                mv.setViewName("portal/addFamily");
            }else{
                //FamilyMember familyMember = familyMemberService.findByFamilyId(familyId);
                PageParam pageParam = familyMemberService.findByPageByFamilyId(pageNum,pageSize,familyIdAndName.getId());
                mv.addObject("familyName", familyIdAndName.getFamilyName());
                mv.addObject("portalFamilyId", familyIdAndName.getId());
                mv.addObject("pageData", pageParam.getResult());
                mv.addObject("pageParam",pageParam);
                List<User> members = userService.findListMembers();
                mv.addObject("members", members);
                mv.setViewName("portal/myFamily");
            }
        } else {
            //家庭成员
            String userFamilyId = familyMemberService.findByMemberId((String) httpSession.getAttribute("userId"));
            PageParam pageParam = familyMemberService.findByPageByFamilyId(pageNum,pageSize,userFamilyId);
            mv.addObject("pageData", pageParam.getResult());
            mv.addObject("pageParam",pageParam);
            List<User> members = userService.findListMembers();
            mv.addObject("members", members);
            mv.setViewName("portal/myFamily");

        }
        return mv;
    }

//    @RequestMapping("/payOrder")
//    public String payOrder(String id) throws Exception {
//        Order order = orderService.findById(id);
//        order.setState(1);
//        orderService.update(order);
//        return REDIRECT+"/myOrder";
//    }

//    @RequestMapping("/deleteOrder")
//    public String deleteOrder(String id) throws Exception {
//        Order order = orderService.findById(id);
//        order.setState(2);
//        orderService.update(order);
//        return REDIRECT+"/myOrder";
//    }

    @RequestMapping("/PortalFamilySave")
    public String familySave(HttpSession httpSession, Family family) throws Exception {
        family.setId(Tools.getUUID());
        family.setOwerId((String) httpSession.getAttribute("userId"));
        family.setState(1);
        familyService.save(family);
        return REDIRECT+"/myFamily";
    }

    @RequestMapping("/deleteMember")
    public String familyDelete(String id ,String familyId) {
        if (Tools.notEmpty(id)) {
            try {
                familyMemberService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        return REDIRECT + "/manager/familyMemberList?familyId="+familyId;
        return REDIRECT+"/myFamily";
    }
}