package com.boot.web.wxgroup.model;

import java.math.BigDecimal;
import java.util.*;

import com.boot.util.BaseEntity;
import com.boot.util.BaseFile;

import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * 
 * @author 赵祖龙
 * 微信用户分组信息表javaBean
 * 2019-09-26
 */
@Table(name="wx_user_group")
public class WxUserGroup extends BaseEntity<WxUserGroup>{
	
	private static final long serialVersionUID = 1L;
	
    private String  userId;      //小组负责人
    private String  userCode;      //用户编码
    private String  username;      //用户名称
    private String  groupCode;      //用户分组编码
    private String  groupCname;      //负责小组名称 广东农信;南京银行MAST;
    private String  isMsg;      //是否要通知微信消息
	@Id
    private String  id;      //主键
    private String  delFlag;      //逻辑删除标记(0.正常，1.删除)
    private String  status;      //状态
    private String  instId;      //机构ID
    private String  loginIp;      //登录IP
    private Date  loginDate;      //登录日期
    private String  createBy;      //创建人
    private Date  createDate;      //创建时间
    private String  updateBy;      //最近修改人
    private Date  updateDate;      //最近修改时间
    private String  remarks;      //描述
	@Transient
	private List<BaseFile>	upfileList;			//批量上传文件list
	
	public WxUserGroup() {
    	super.setModuleName("微信用户分组信息表");
	}
	/**
	 * 小组负责人
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	/**
	 * 用户编码
	 * @param userCode
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserCode() {
		return userCode;
	}
	/**
	 * 用户名称
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	/**
	 * 用户分组编码
	 * @param groupCode
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * 负责小组名称 广东农信;南京银行MAST;
	 * @param groupCname
	 */
	public void setGroupCname(String groupCname) {
		this.groupCname = groupCname;
	}
	public String getGroupCname() {
		return groupCname;
	}
	/**
	 * 是否要通知微信消息
	 * @param isMsg
	 */
	public void setIsMsg(String isMsg) {
		this.isMsg = isMsg;
	}
	public String getIsMsg() {
		return isMsg;
	}
	/**
	 * 主键
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	/**
	 * 逻辑删除标记(0.正常，1.删除)
	 * @param delFlag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 状态
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	/**
	 * 机构ID
	 * @param instId
	 */
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstId() {
		return instId;
	}
	/**
	 * 登录IP
	 * @param loginIp
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginIp() {
		return loginIp;
	}
	/**
	 * 登录日期
	 * @param loginDate
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	/**
	 * 创建人
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 创建时间
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 最近修改人
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 最近修改时间
	 * @param updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 描述
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 批量上传文件list
	 * @param upfileList
	 */
	public List<BaseFile> getUpfileList() {
		return upfileList;
	}
	public void setUpfileList(List<BaseFile> upfileList) {
		this.upfileList = upfileList;
	}

	@Override
	public String toString() {
		return "微信用户分组信息表： {\"userId\": \""+userId+"\", \"userCode\": \""+userCode+"\", \"username\": \""+username+"\", \"groupCode\": \""+groupCode+"\", \"groupCname\": \""+groupCname+"\", \"isMsg\": \""+isMsg+"\", \"id\": \""+id+"\", \"delFlag\": \""+delFlag+"\", \"status\": \""+status+"\", \"instId\": \""+instId+"\", \"loginIp\": \""+loginIp+"\", \"loginDate\": \""+loginDate+"\", \"createBy\": \""+createBy+"\", \"createDate\": \""+createDate+"\", \"updateBy\": \""+updateBy+"\", \"updateDate\": \""+updateDate+"\", \"remarks\": \""+remarks+"\"}";
	}

  public  WxUserGroup(Map<String, Object> row)throws ParseException{
         if( row.get("userId") !=null  ){
          String strValue=row.get("userId").toString();
          this.setUserId(strValue);
        }
         if( row.get("userCode") !=null  ){
          String strValue=row.get("userCode").toString();
          this.setUserCode(strValue);
        }
         if( row.get("username") !=null  ){
          String strValue=row.get("username").toString();
          this.setUsername(strValue);
        }
         if( row.get("groupCode") !=null  ){
          String strValue=row.get("groupCode").toString();
          this.setGroupCode(strValue);
        }
         if( row.get("groupCname") !=null  ){
          String strValue=row.get("groupCname").toString();
          this.setGroupCname(strValue);
        }
         if( row.get("isMsg") !=null  ){
          String strValue=row.get("isMsg").toString();
          this.setIsMsg(strValue);
        }
         if( row.get("id") !=null  ){
          String strValue=row.get("id").toString();
          this.setId(strValue);
        }
         if( row.get("delFlag") !=null  ){
          String strValue=row.get("delFlag").toString();
          this.setDelFlag(strValue);
        }
         if( row.get("status") !=null  ){
          String strValue=row.get("status").toString();
          this.setStatus(strValue);
        }
         if( row.get("instId") !=null  ){
          String strValue=row.get("instId").toString();
          this.setInstId(strValue);
        }
         if( row.get("loginIp") !=null  ){
          String strValue=row.get("loginIp").toString();
          this.setLoginIp(strValue);
        }
       /*
         if( row.get("loginDate") !=null  ){
         SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String strValue=row.get("loginDate").toString();
         this.setLoginDate(sdf.parse(strValue));
        }
       */
         if( row.get("createBy") !=null  ){
          String strValue=row.get("createBy").toString();
          this.setCreateBy(strValue);
        }
       /*
         if( row.get("createDate") !=null  ){
         SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String strValue=row.get("createDate").toString();
         this.setCreateDate(sdf.parse(strValue));
        }
       */
         if( row.get("updateBy") !=null  ){
          String strValue=row.get("updateBy").toString();
          this.setUpdateBy(strValue);
        }
       /*
         if( row.get("updateDate") !=null  ){
         SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String strValue=row.get("updateDate").toString();
         this.setUpdateDate(sdf.parse(strValue));
        }
       */
         if( row.get("remarks") !=null  ){
          String strValue=row.get("remarks").toString();
          this.setRemarks(strValue);
        }
  }

}