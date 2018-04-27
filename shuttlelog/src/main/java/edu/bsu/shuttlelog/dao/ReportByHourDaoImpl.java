package edu.bsu.shuttlelog.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.reqpojo.DataSummaryReq;
import edu.bsu.shuttlelog.resppojo.DataSummaryByHour;

@Repository
public class ReportByHourDaoImpl implements ReportHourDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(ReportByHourDaoImpl.class);

	@Override
	@Transactional
	public List<DailyReportByHour> getByDate(Date date) throws Exception {

		Session currentSession = sessionFactory.getCurrentSession();

		List<DailyReportByHour> resultList = new ArrayList<DailyReportByHour>();

		List list = currentSession.createQuery("from DailyReportByHour where  RecordDate =date ")
				.setParameter("date", date).list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DailyReportByHour drh = (DailyReportByHour) iterator.next();
			resultList.add(drh);
			logger.debug(drh);
		}
		logger.debug("resultList" + resultList.toString());

		return resultList;

	}

	@Override
	@Transactional
	public List<DataSummaryByHour> getBySummary(DataSummaryReq req) throws Exception {

		Session currentSession = sessionFactory.getCurrentSession();

		List<DataSummaryByHour> resultList = new ArrayList<DataSummaryByHour>();

		List list = currentSession.createNativeQuery(
				"select Record_hour, sum(NUMBER_BOARDED), sum(NUMBER_LEFT) from  DAILYREPORT_hour where Record_Date >= :begin AND Record_Date <= :end and loop_Name = :loopName group by Record_hour")
				.setParameter("begin", req.getFromDate()).setParameter("end", req.getToDate())
				.setParameter("loopName", req.getLoopName()).list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] row = (Object[]) iterator.next();
			DataSummaryByHour dsh = new DataSummaryByHour();
			BigDecimal bdHour =  (BigDecimal) row[0];
			dsh.setHour(bdHour.toString());
			dsh.setTotalNumBoarded((BigDecimal) row[1]);
			dsh.setTotalNumLeft((BigDecimal) row[2]);
			resultList.add(dsh);

		}
		logger.debug("resultList" + resultList.toString());
		return resultList;
	}

}
