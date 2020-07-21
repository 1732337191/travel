package com.cn.travel.role.role.entity;

import com.cn.travel.base.entity.BaseDomain;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Role extends BaseDomain {
    private String roleName;

    private Integer state;
}
