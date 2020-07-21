package com.cn.travel.family.entity;

import com.cn.travel.base.entity.BaseDomain;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Family extends BaseDomain {
    private String familyName;

    private String familyAddress;

    private Integer state;

    private String owerId;
}
