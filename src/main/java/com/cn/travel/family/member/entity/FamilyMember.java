package com.cn.travel.family.member.entity;

import com.cn.travel.base.entity.BaseDomain;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class FamilyMember extends BaseDomain {
    private String memberId;

    private String familyId;

}
