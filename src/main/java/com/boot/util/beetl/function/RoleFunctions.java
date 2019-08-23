package com.boot.util.beetl.function;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.boot.web.sys.model.SysRole;
import com.boot.web.sys.service.SysRoleService;
import com.boot.util.SysUserUtils;

@Component
public class RoleFunctions {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 用户的角色List形式(当前登录用户持有的角色)
     */
    public List<SysRole> getUserRoleList() {
        return sysRoleService.findCurUserRoleList();
    }

    /**
     * 用户角色map形式 key:角色id
     */
    public Map<String, SysRole> getUserRoleMap() {
        return SysUserUtils.getUserRolesMap();
    }

}
