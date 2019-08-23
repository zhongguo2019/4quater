package com.boot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import tk.mybatis.spring.annotation.MapperScan;
import com.boot.util.ConfigUtil;
import com.boot.util.CurrentWeek;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
 

@ServletComponentScan
@SpringBootApplication(scanBasePackages ={"com.boot", "com.krm"})
@MapperScan({"com.boot.web","com.boot.util"})
//@tk.mybatis.spring.annotation.MapperScan(basePackages = { "com.boot.web","com.boot.util"})
public class Application implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Application.class);
        System.setProperty("user.timezone","Asia/Shanghai");
        Environment env = app.run(args).getEnvironment();
        LOGGER.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
		//SpringApplication.run(Application.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		// TODO Auto-generated method stub
		LOGGER.info(ConfigUtil.getValue("db.schema"));
		String sql = "SELECT id FROM " + ConfigUtil.getValue("db.schema")
				+ "t_happy_work_title WHERE begindate=? AND enddate=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { CurrentWeek.getCurrenproDay("yyyy-MM-dd"), CurrentWeek.getCurrenaftDay("yyyy-MM-dd") });
		if (list.size() == 0) {
			sql = "INSERT INTO t_happy_work_title (id,work_title,begindate,enddate) VALUES (?,?,?,?)";
			String id = UUID.randomUUID().toString();
			Date begindate = (Date) CurrentWeek.getCurrenproDate();
			Date enddate = (Date) CurrentWeek.getCurrenaftDate();
			Calendar calendar = Calendar.getInstance();
			String work_title = calendar.get(Calendar.YEAR) + "年" + CurrentWeek.getMonth() + "月第"
					+ CurrentWeek.getWeekOfMonth() + "周";

			LOGGER.info("CurrentWeek.getCurrenproDate()" + CurrentWeek.getCurrenproDate().toString());

			jdbcTemplate.update(sql, new Object[] { id, work_title, begindate, enddate });

		}

	}

}