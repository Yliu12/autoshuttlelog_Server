package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.ReportHourDao;
import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryByHour;

@Service
@Transactional(readOnly = true)
public class ReportByHourServiceImpl implements ReportByHourService {
	@Autowired
	ReportHourDao dailyReportHour;

	@Override
	@Transactional
	public List<DailyReportByHour> getByDate(Date date) throws Exception {

		return dailyReportHour.getByDate(date);

	}

	@Override
	public List<DataSummaryByHour> getBySummary(DataSummaryReq req) throws Exception {
		// TODO Auto-generated method stub
		return dailyReportHour.getBySummary(req);
	}

}
