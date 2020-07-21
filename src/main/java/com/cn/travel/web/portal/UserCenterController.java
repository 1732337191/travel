package com.cn.travel.web.portal;

import com.cn.travel.family.member.entity.FamilyMember;
import com.cn.travel.family.member.service.imp.FamilyMemberService;
import com.cn.travel.utils.Tools;
import com.cn.travel.utils.service.MailService;
import com.cn.travel.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserCenterController extends BaseController {
    @Autowired
    private MailService mailService;
    @Autowired
    FamilyMemberService familyMemberService;

    @RequestMapping("/userCenter")
    public ModelAndView userCenter() {
        ModelAndView mv = this.getModeAndView();
        mv.setViewName("portal/userCenter");
        return mv;
    }

    @RequestMapping("/inviteAddFamily")
    @ResponseBody
    public String getCode(@RequestParam(value = "to", required = true) String to,
                          @RequestParam(value = "familyId", required = true) String familyId,
                          @RequestParam(value = "familyName", required = true) String familyName,
                          @RequestParam(value = "owerName", required = true) String owerName) {

//        if(to != null && !to.equals("")){
//            text = EmailCodeUtils.getRes();
//        }
        mailService.send(to, familyId, familyName, owerName);
        String text = "已发送邀请邮件";
        return text;
    }

    @RequestMapping("/inviteUserAdd")
    public String inviteUserAdd(HttpSession httpSession, @RequestParam(value = "familyId", required = true) String familyId, FamilyMember entity) throws Exception {
        System.out.println(familyId);
        String userId = (String) httpSession.getAttribute("userId");
        String roleId = (String) httpSession.getAttribute("roleId");
        if (Tools.isEmpty(userId)) {
            return REDIRECT + "/goLogin";
        } else {
            FamilyMember object = familyMemberService.findByMemberIdAndFamilyId(userId,familyId);
            if (object == null && !roleId.equals("797e8071ac174de6ab01745273bb28a7")) {
                entity.setId(Tools.getUUID());//生成主键
                entity.setFamilyId(familyId);
                entity.setMemberId(userId);
                familyMemberService.save(entity);
                return REDIRECT + "/myFamily";
            }else{
                return REDIRECT + "/myFamily";
            }
        }

    }
}
