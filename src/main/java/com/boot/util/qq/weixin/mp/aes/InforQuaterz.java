package com.boot.util.qq.weixin.mp.aes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.boot.util.CommonEntity;
import com.boot.util.DateUtils;
import com.boot.util.SpringContextHolder;
import com.boot.web.todaywork.service.DoufuTodayWorkService;

import org.quartz.Job;

public class InforQuaterz implements Job {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	DoufuTodayWorkService doufuTodayWorkService = SpringContextHolder.getBean("doufuTodayWorkService");

	public InforQuaterz() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {

		String reportToday = DateUtils.DateToStr8(new Date());
		String reportPreday = DateUtils.getPreDateByDate8(reportToday);
		String notCommitJX = queryNotCommitUser("江西", reportPreday);
		String notCommitJS = queryNotCommitUser("江苏", reportPreday);
		String msgContent = "昨天日报，小组成员都已经提交成功！";
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		if ("".equals(notCommitJX)) {
			msgContent = "江西农信项目组,[" + reportPreday  + "],日报全部提交！";
			weiXinUtil.SendTextcardMessage("ZhaoZuLong", msgContent);
			weiXinUtil.SendTextcardMessage("bechalin", msgContent);
		} else {
			msgContent = "江西农信项目组,["+reportPreday+"],日报未提交人员如下:\n" + notCommitJX + "\n请及时通知本人，尽快补上！";
			weiXinUtil.SendTextcardMessage("ZhaoZuLong", msgContent);
			weiXinUtil.SendTextcardMessage("bechalin", msgContent);
		}

		if ("".equals(notCommitJS)) {
			msgContent = "江苏农信项目组,[" + reportPreday + "],日报全部提交！";
			weiXinUtil.SendTextcardMessage("ZhaoZuLong", msgContent);
			weiXinUtil.SendTextcardMessage("SiBaDaKeSi", msgContent);
		} else {
			msgContent = "江苏农信项目组,[" + reportPreday + "],日报未提交人员如下:\n" + notCommitJS + "\n请及时通知本人，尽快补上！";
			weiXinUtil.SendTextcardMessage("ZhaoZuLong", msgContent);
			weiXinUtil.SendTextcardMessage("SiBaDaKeSi", msgContent);
		}

	}

	public String queryNotCommitUser(String groupname, String reportdate) {
		String strRtn = "";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupname", groupname);
		params.put("reportdate", reportdate);
		List<CommonEntity> lst = doufuTodayWorkService.queryNotCommitUser(params);
		String userName, userAccount;
		if (lst.size() != 0) {
			for (int i = 0; i < lst.size(); i++) {
				Map<String, Object> mpGroupName = (Map<String, Object>) lst.get(i);
				userName = mpGroupName.get("name").toString();
				userAccount = mpGroupName.get("account").toString();
				if (i == 0) {
					strRtn = userName;
				} else {
					strRtn = strRtn + "、" + userName;
				}

			}
		}

		return strRtn;

	}
}