package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.DailyReportHourDao;
import edu.bsu.shuttlelog.dao.DailyReportStopDao;
import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;

@Service
@Transactional(readOnly = true)
public class DailyReportStopServiceImpl implements DailyReportStopService {
	@Autowired
	DailyReportStopDao dailyReportStop;

	@Override
	@Transactional
	public List<DailyReportByStop> getByDate(Date date) throws Exception {

		return dailyReportStop.getByDate(date);

	}

}
