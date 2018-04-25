package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.DailyReportHourDao;
import edu.bsu.shuttlelog.entity.DailyReportByHour;

@Service
@Transactional(readOnly = true)
public class DailyReportHourServiceImpl implements DailyReportHourService {
	@Autowired
	DailyReportHourDao dailyReportHour;

	@Override
	@Transactional
	public List<DailyReportByHour> getByDate(Date date) throws Exception {

		return dailyReportHour.getByDate(date);

	}

}
