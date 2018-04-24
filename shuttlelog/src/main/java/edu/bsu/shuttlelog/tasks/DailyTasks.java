package edu.bsu.shuttlelog.tasks;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.bsu.shuttlelog.service.BaseService;

@Component
public class DailyTasks {
	@Autowired
	private BaseService baseService;

	@Scheduled(cron = "0 0 5 ? * *")
	public void DailySummaryHour() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // Format: 2016/11/16 12:08:43

		System.out.println("do something");
		try {
			String sql = "INSERT INTO dailyreport_hour \n" + "            (record_date, \n" + "             hour, \n"
					+ "             loop_name, \n" + "             number_boarded, \n" + "             number_left) \n"
					+ "SELECT Cast(record_time AS date) AS date, \n" + "       Hour(record_time)         AS hour, \n"
					+ "       loop_name, \n" + "       Sum(number_boarded)       AS NUMBER_BOARDED, \n"
					+ "       Sum(number_left)          AS NUMBER_LEFT \n" + "FROM   log \n"
					+ "WHERE  Cast(record_time AS date) = Subdate(CURRENT_DATE, 1) \n"
					+ "GROUP  BY Hour(record_time), \n" + "          loop_name, \n"
					+ "          Cast(record_time AS date); ";
			int result = baseService.excuteBySql(sql);
			System.out.println("---009:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Scheduled(cron = "0 0 5 ? * *")
	public void DailySummaryStop() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // Format: 2016/11/16 12:08:43

		System.out.println("do something");
		try {
			String sql = "INSERT INTO yliu12.dailyreport_stop \n" + "            (record_date, \n"
					+ "             stop_name, \n" + "             loop_name, \n" + "             number_boarded, \n"
					+ "             number_left) \n" + "SELECT Cast(record_time AS date) AS Record_date, \n"
					+ "       stop_short                AS STOP_NAME, \n" + "       loop_name, \n"
					+ "       Sum(number_boarded)       AS NUMBER_BOARDED, \n"
					+ "       Sum(number_left)          AS NUMBER_LEFT \n" + "FROM   yliu12.log \n"
					+ "WHERE  Cast(record_time AS date) = Subdate(CURRENT_DATE, 1) \n" + "GROUP  BY stop_short, \n"
					+ "          loop_name, \n" + "          Cast(record_time AS date); ";
			int result = baseService.excuteBySql(sql);
			System.out.println("---009:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
