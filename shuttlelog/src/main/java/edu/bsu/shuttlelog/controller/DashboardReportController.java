package edu.bsu.shuttlelog.controller;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;
import edu.bsu.shuttlelog.service.DailyReportHourService;
import edu.bsu.shuttlelog.service.DailyReportStopService;

@Controller
public class DashboardReportController {
	@Autowired
	DailyReportHourService dailyReportHourService;
	@Autowired
	DailyReportStopService dailyReportStopService;


	private static Logger logger = Logger.getLogger(DashboardReportController.class);

	@GetMapping("/hourlydata/{date}")
	public ResponseEntity<?> getDailyDataByHour(@PathVariable("date") long timestamp) {
		try {
			Date date = new Date(timestamp);
			System.out.println(date);

			List<DailyReportByHour> drh = dailyReportHourService.getByDate(date);
			return ResponseEntity.ok().body(drh);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

	@GetMapping("/databystop/{date}")
	public ResponseEntity<?> getDailyDataByStop(@PathVariable("date") long timestamp) {
		try {
			Date date = new Date(timestamp);
			System.out.println(date);

			List<DailyReportByStop> drh = dailyReportStopService.getByDate(date);
			return ResponseEntity.ok().body(drh);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

}
