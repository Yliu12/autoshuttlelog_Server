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
			String sql = "insert into  DAILYREPORT_hour(\n" + 
					"    Record_date,\n" + 
					"    hour,\n" + 
					"    LOOP_NAME,\n" + 
					"    NUMBER_BOARDED,\n" + 
					"    NUMBER_LEFT\n" + 
					")\n" + 
					"SELECT record_date,\n" + 
					"       HOUR,\n" + 
					"       loop_name,\n" + 
					"       SUM(number_boarded) AS NUMBER_BOARDED,\n" + 
					"       SUM(number_left)    AS NUMBER_LEFT\n" + 
					"FROM   (SELECT DISTINCT Concat(record_time, bus_id) AS Hash,\n" + 
					"                         TRUNC(record_time)   AS Record_date,\n" + 
					"                        EXTRACT(HOUR from record_time)  AS HOUR,\n" + 
					"                        loop_name,\n" + 
					"                        number_boarded,\n" + 
					"                        number_left\n" + 
					"        FROM   log where TRUNC(record_time) = TRUNC(SYSDATE) - 1)\n" + 
					"GROUP  BY HOUR,\n" + 
					"          loop_name,\n" + 
					"          record_date;";
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
			String sql = "INSERT INTO dailyreport_stop \n" + 
					"            (record_date, \n" + 
					"             stop_name, \n" + 
					"             loop_name, \n" + 
					"             number_boarded, \n" + 
					"             number_left) \n" + 
					"SELECT record_date, \n" + 
					"       stop_name, \n" + 
					"       loop_name, \n" + 
					"       SUM(number_boarded) AS NUMBER_BOARDED, \n" + 
					"       SUM(number_left)    AS NUMBER_LEFT \n" + 
					"FROM   (SELECT DISTINCT Concat(record_time, bus_id) AS Hash, \n" + 
					"                        Trunc(record_time)          AS Record_date, \n" + 
					"                        stop_short                  AS STOP_NAME, \n" + 
					"                        loop_name, \n" + 
					"                        number_boarded, \n" + 
					"                        number_left \n" + 
					"        FROM   log log where TRUNC(record_time) = TRUNC(SYSDATE) - 1) \n" + 
					"GROUP  BY stop_name, \n" + 
					"          loop_name, \n" + 
					"          record_date; ";
			int result = baseService.excuteBySql(sql);
			System.out.println("---009:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
