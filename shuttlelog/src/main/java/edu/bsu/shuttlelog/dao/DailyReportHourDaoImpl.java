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

@Repository
public class DailyReportHourDaoImpl implements DailyReportHourDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(DailyReportHourDaoImpl.class);

	@Override
	@Transactional
	public List<DailyReportByHour> getByDate(Date date) throws Exception {
		logger.debug("date" + date);
		Session currentSession = sessionFactory.getCurrentSession();
		List<DailyReportByHour> resultList = new ArrayList<DailyReportByHour>();
		List list = currentSession.createQuery("from DailyReportByHour where RecordDate = :date")
				.setParameter("date", date).list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DailyReportByHour drh = (DailyReportByHour) iterator.next();
			resultList.add(drh);
			logger.debug(drh);
		}
		logger.debug("resultList" + resultList.toString());

		return resultList;

	}
}
