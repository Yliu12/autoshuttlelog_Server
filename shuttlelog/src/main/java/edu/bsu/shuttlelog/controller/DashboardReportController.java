package edu.bsu.shuttlelog.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryPojo;
import edu.bsu.shuttlelog.service.ReportByHourService;
import edu.bsu.shuttlelog.service.ReportByStopService;

@Controller
public class DashboardReportController {
	@Autowired
	ReportByHourService dailyReportHourService;
	@Autowired
	ReportByStopService dailyReportStopService;

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

	@PostMapping("/datasummary")
	public ResponseEntity<?> getDailyDataByStop(@RequestBody DataSummaryReq dataSummaryReq) {
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			System.out.println(dataSummaryReq);
			logger.debug("DashboardReportController.getDailyDataByStop " + dataSummaryReq);

			resultMap.put("byHour", dailyReportHourService.getBySummary(dataSummaryReq));
			resultMap.put("byStop", dailyReportStopService.getBySummary(dataSummaryReq));

			return ResponseEntity.ok().body(resultMap);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}

}
