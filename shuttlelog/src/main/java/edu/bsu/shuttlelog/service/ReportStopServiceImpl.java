package edu.bsu.shuttlelog.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.dao.ReportByStopDao;
import edu.bsu.shuttlelog.entity.DailyReportByStop;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryPojo;

@Service
@Transactional(readOnly = true)
public class ReportStopServiceImpl implements ReportByStopService {
	@Autowired
	ReportByStopDao dailyReportStop;

	@Override
	@Transactional
	public List<DailyReportByStop> getByDate(Date date) throws Exception {

		return dailyReportStop.getByDate(date);

	}

	@Override
	public List<DataSummaryPojo> getBySummary(DataSummaryReq req) throws Exception {
		// TODO Auto-generated method stub
		return dailyReportStop.getBySummary(req);
	}

}
