package edu.bsu.shuttlelog.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.bsu.shuttlelog.entity.DailyReportByHour;
import edu.bsu.shuttlelog.entity.DailyReportByStop;

@Repository
public class ReportByStopDaoImpl implements ReportByStopDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(ReportByStopDaoImpl.class);

	@Override
	@Transactional
	public List<DailyReportByStop> getByDate(Date date) throws Exception {
		Session currentSession = sessionFactory.getCurrentSession();
		List<DailyReportByStop> resultList = new ArrayList<DailyReportByStop>();
		java.util.Date newDate = new java.util.Date(date.getTime());

		// Calendar cal = Calendar.getInstance();
		// cal.setTime(newDate);
		// cal.set(Calendar.HOUR_OF_DAY, 0);
		// cal.set(Calendar.MINUTE, 0);
		// cal.set(Calendar.SECOND, 0);
		// java.util.Date begin = cal.getTime();
		// cal.add(Calendar.DATE, 1);
		// java.util.Date end = cal.getTime();
		//
		// logger.debug("begin" + begin);
		// logger.debug("end" + end);

		// List list = currentSession.createQuery("from DailyReportByStop where
		// RecordDate > :begin AND RecordDate < :end")
		// .setParameter("begin", begin).setParameter("end", end).list();

		List list = currentSession.createQuery("from DailyReportByStop where RecordDate = :date")
				.setParameter("date", date).list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DailyReportByStop drh = (DailyReportByStop) iterator.next();
			resultList.add(drh);
			logger.debug(drh);
		}
		logger.debug("resultList" + resultList.toString());

		return resultList;

	}
}
