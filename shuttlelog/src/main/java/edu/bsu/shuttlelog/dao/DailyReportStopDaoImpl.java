package edu.bsu.shuttlelog.dao;

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
import edu.bsu.shuttlelog.entity.DailyReportByStop;

@Repository
public class DailyReportStopDaoImpl implements DailyReportStopDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(DailyReportStopDaoImpl.class);

	@Override
	@Transactional
	public List<DailyReportByStop> getByDate(Date date) throws Exception {
		logger.debug("date" + date);
		Session currentSession = sessionFactory.getCurrentSession();
		List<DailyReportByStop> resultList = new ArrayList<DailyReportByStop>();
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
