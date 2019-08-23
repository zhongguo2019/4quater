package com.boot.web.sys.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.boot.util.ServiceMybatis;
import com.boot.util.beetl.function.OrganFunctions;
import com.boot.util.IPUtils;
import com.boot.util.encrypt.PasswordEncoder;
import com.boot.web.sys.model.SysOrgan;
import com.boot.web.sys.model.SysUser;
import com.boot.util.SysUserUtils;

@Service
public class SysUserCenterService extends ServiceMybatis<SysUser> {

    @Resource
    private OrganFunctions organFunctions;

    public SysUser getSysUserInfo() {
        SysUser sysUser = SysUserUtils.getCacheLoginUser();
        Map<String, SysOrgan> organs = organFunctions.getAllOrganMap();
        String organId = sysUser.getOrganId();
        String deptId = sysUser.getDeptId();
        String orgStr = null;
        if (deptId.equals(organId)) { // 机构
            orgStr = ((SysOrgan) organs.get(organId)).getName();
        } else {
            orgStr = ((SysOrgan) organs.get(organId)).getName() + " —— "
                    + ((SysOrgan) organs.get(deptId)).getName();
        }
        String curIP = IPUtils.getClientAddress(SysUserUtils.getCurRequest());
        String ipEx = "";
        if (!StringUtils.equals(sysUser.getLoginIp(), curIP))
            ipEx = "(当前IP为:" + curIP + "，与上次登录IP不一致，请注意!)";
//		sysUser.set("orgStr", orgStr);
//		sysUser.set("ipEx", ipEx);
        return sysUser;
    }

    /**
     * 用户更新资料
     */
    public Integer updateSysuserInfo(SysUser sysUser) {
        String pwd = null;
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            pwd = PasswordEncoder.encrypt(sysUser.getPassword(),
                    SysUserUtils.getCacheLoginUser().getUsername());
        }
        sysUser.setPassword(pwd);
        sysUser.setId(SysUserUtils.getCacheLoginUser().getId());
        return this.updateByPrimaryKeySelective(sysUser);
    }

}
