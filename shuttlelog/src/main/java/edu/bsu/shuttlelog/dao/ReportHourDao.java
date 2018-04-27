package edu.bsu.shuttlelog.dao;

import java.sql.Date;
import java.util.List;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryPojo;

public interface ReportHourDao {
	public List<DailyReportByHour> getByDate(Date date) throws Exception;

	public List<DataSummaryPojo> getBySummary(DataSummaryReq req) throws Exception;
}
