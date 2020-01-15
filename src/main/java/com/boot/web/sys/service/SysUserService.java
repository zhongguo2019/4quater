

package com.boot.web.sys.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.boot.util.CommonEntity;
import com.boot.util.ServiceMybatis;
import com.krm.common.constant.Constant;
import com.boot.util.CacheUtils;
import com.boot.util.StringUtil;
import com.boot.util.encrypt.PasswordEncoder;
import com.boot.web.sys.mapper.SysOrganMapper;
import com.boot.web.sys.mapper.SysRoleMapper;
import com.boot.web.sys.mapper.SysUserMapper;
import com.boot.web.sys.model.SysUser;
import com.boot.util.SysUserUtils;

/**
 * @author
 */

@Service("sysUserService")
public class SysUserService extends ServiceMybatis<SysUser> {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysOrganMapper sysOrganMapper;

    /**
     * 添加或更新用户
     *
     * @param sysUser
     * @return
     */
    public int saveSysUser(SysUser sysUser) {
        int count = 0;
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", sysUser.getOrganId());
        if (StringUtils.isNotBlank(sysUser.getPassword())) {
            String encryptPwd = PasswordEncoder.encrypt(sysUser.getPassword(), sysUser.getUsername());
            sysUser.setPassword(encryptPwd);
        } else {
            sysUser.setPassword(null);
        }
        if (StringUtil.isEmpty(sysUser.getId())) {
            String id = generateId();
            sysUser.setId(id);
            sysUser.setDelFlag("0");
            sysUser.setStatus(Constant.USER_STATUS_NORMAL);
            sysUser.setCreateDate(new Date());
            sysUser.setCreateBy(SysUserUtils.getCacheLoginUser().getId().toString());
            sysUser.setDelFlag(Constant.DEL_FLAG_NORMAL);
            sysUser.setUserType("2");
            count = this.insertSelective(sysUser);
        } else {
            logger.info("根据用户ID删除用户角色配置信息");
            sysRoleMapper.deleteUserRoleByUserId(sysUser.getId());
            count = this.updateByPrimaryKeySelective(sysUser);

            logger.info("开始清除缓存");
            SysUserUtils.clearAllCachedAuthorizationInfo(Arrays.asList(sysUser.getId()));
            if (CacheUtils.isCacheByKey(Constant.CACHE_SYS_USER, sysUser.getId().toString())) {
                String userType = this.selectByPrimaryKey(sysUser.getId()).getUserType();
                sysUser.setUserType(userType);
//				SysUserUtils.cacheLoginUser(sysUser);
            }
        }
        if (sysUser.getRoleIds() != null) {
            logger.info("添加用户角色配置信息");
            sysRoleMapper.insertUserRoleByUserId(sysUser);
        }
        return count;
    }

    /**
     * 保存注册用户
     *
     * @param user
     * @return
     */
    public int saveRegisteredUser(SysUser user) {
        int count = this.insertSelective(user);
        if (user.getRoleIds() != null) {
            logger.info("添加用户角色配置信息");
            sysRoleMapper.insertUserRoleByUserId(user);
        }
        return count;
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    public int deleteUser(String userId) {
        logger.info("根据用户ID删除用户角色配置信息");
        SysUser user = this.selectByPrimaryKey(userId);
        if (user.isAdmin()) {
            return -1;
        }
        if (SysUserUtils.getCacheLoginUser().getId().equals(userId)) {
            return -2;
        }
        sysRoleMapper.deleteUserRoleByUserId(userId);
        SysUserUtils.clearAllCachedAuthorizationInfo(Arrays.asList(userId));
        SysUserUtils.clearCacheUser(userId);
        return this.updateDelFlagToDelStatusById(SysUser.class, userId);
    }

    /**
     * 用户列表
     *
     * @param params
     * @return
     */
    public PageInfo<CommonEntity> findPageInfo(Map<String, Object> params) {
        logger.info("分页查询用户");
        params.put(Constant.CACHE_USER_DATASCOPE, SysUserUtils.dataScopeFilterString("so", "su", "id"));
        params.put("userType", SysUserUtils.getCacheLoginUser().getUserType());
        PageHelper.startPage(params);
        List<CommonEntity> list = sysUserMapper.findPageInfo(params);
        return new PageInfo<CommonEntity>(list);
    }

    /**
     * 验证用户
     *
     * @param username 用户名
     * @param password 密码
     * @return user
     */
    public SysUser checkUser(String username, String password) {
        SysUser sysUser = new SysUser();
        String secPwd = PasswordEncoder.encrypt(password, username);
        sysUser.setUsername(username);
        sysUser.setPassword(secPwd);
        List<SysUser> users = this.select(sysUser);
        if (users != null && users.size() == 1 && users.get(0) != null) {
            return users.get(0);
        }
        return null;
    }
    /**
     * 根据登录用户名得到用户信息放到实体中
     * 从单点登录进入到系统中，不再判断用户名称密码
     * 
     *
     */ 
    public SysUser selectUserByName(Map<String, Object> params) {
		return sysUserMapper.selectUserByName( params);  
	}
    
    /**
     * 
     * 验证用户的有效性
     * 
     *
     */ 

	public SysUser verifyLogin(Map<String, Object> userInfo) {
		return selectUserByName(userInfo);
	}
    /**
     * 
     * 验证用户的有效性
     * 
     *
     */ 

	public SysUser queryOneUser(Map<String, Object> userInfo) {
		return sysUserMapper.queryOneUser(userInfo);
	}	

	public List<CommonEntity>getUserModule(Map<String, Object> userInfo) {
		 List<CommonEntity> list =  sysUserMapper.getUserGroup(userInfo.get("username").toString());
		 if(list.isEmpty()) {
			 return null;
		 }
			Map<String, Object> mpGroupName = (Map<String, Object>) list.get(0);
			logger.info("Map<String, Object> mpGroupName"+mpGroupName.get("group_name"));
			
			List<CommonEntity>  listPro = sysUserMapper.getProdOfProj(mpGroupName.get("group_name").toString());
			
			Map<String, Object> mpPro =  listPro.get(0);
			logger.info("Map<String, Object> mpPro"+mpGroupName.get("product_id"));
			List<CommonEntity>  listModu = sysUserMapper.getModuOfProd(mpPro.get("product_id").toString());
		
        return listModu;
	}
	

	
	public String getProjectGroupId(Map<String, Object> userInfo) {
	String userProjectGroupId="";	
	 List<CommonEntity> list =  sysUserMapper.getUserGroup(userInfo.get("username").toString());
	 if(list.size() !=0) {
		Map<String, Object> mpGroupName = (Map<String, Object>) list.get(0);
		userProjectGroupId = mpGroupName.get("group_id").toString();
	 }else {
		 userProjectGroupId="zt_usergroup";
	 }
     return userProjectGroupId;
	}
	
	
}
