package educaid.logic.db;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import educaid.dal.TestRecordDao;
import educaid.data.TestRecord;
import educaid.logic.NotFoundException;
import educaid.logic.TestRecordService;

@Service
public class TestRecordServiceDb implements TestRecordService {
	private TestRecordDao testDao;

	@Autowired
	public TestRecordServiceDb(TestRecordDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public TestRecord create(TestRecord test) {
		// TODO Auto-generated method stub
		test.setId(UUID.randomUUID().toString());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 3);
		Date date = cal.getTime();
		test.setCreatedTimestamp(date);
		return testDao.save(test);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestRecord> getAllByUserAndCategoryAndDate(String userEmail, String category, String time,
			String sortBy, String sortOrder, int size, int page) {
		int numOfDays = 0;
		Date newDate;
		switch (time.toLowerCase()) {
		case "lastday":
			numOfDays = -1;
			break;
		case "lastweek":
			numOfDays = -7;
			break;
		case "lastmonth":
			numOfDays = -30;
			break;
		}
		newDate = getSpecificDate(numOfDays);
		return this.testDao.findAllByUserEmailAndCategoryAndCreatedTimestampGreaterThan(userEmail, category, newDate,
				PageRequest.of(page, size, Direction.fromString(sortOrder), sortBy));

	}

	@Override
	@Transactional(readOnly = true)
	public List<TestRecord> getAllByUserAndCategory(String userEmail, String category, String sortBy, String sortOrder,
			int size, int page) {
		// TODO Auto-generated method stub
		return this.testDao.findAllByUserEmailAndCategory(userEmail, category,
				PageRequest.of(page, size, Direction.fromString(sortOrder), sortBy));
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestRecord> getAllByUserAndDate(String userEmail, String time, String sortBy, String sortOrder,
			int size, int page) {
		int numOfDays = 0;
		Date newDate;
		switch (time.toLowerCase()) {
		case "lastday":
			numOfDays = -1;
			break;
		case "lastweek":
			numOfDays = -7;
			break;
		case "lastmonth":
			numOfDays = -30;
			break;
		}
		newDate = getSpecificDate(numOfDays);
		return this.testDao.findAllByUserEmailAndCreatedTimestampGreaterThan(userEmail, newDate,
				PageRequest.of(page, size, Direction.fromString(sortOrder), sortBy));
	}

	@Override
	@Transactional(readOnly = true)
	public List<TestRecord> getAllByUser(String userEmail, String sortBy, String sortOrder, int size, int page) {
		return this.testDao.findAllByUserEmail(userEmail,
				PageRequest.of(page, size, Direction.fromString(sortOrder), sortBy));
	}	
	

	@Override
	@Transactional(readOnly = true)
	public long findStatisticsByCategoryAndUserEmail(String action,String category,String userEmail) {
		try {
		switch(action.toLowerCase()){
		case "count":
			return this.testDao.countByCategoryAndUserEmail(category, userEmail);
		case "average":
			return this.testDao.findAverageByCategoryAndUserEmail(category, userEmail);
		case "min":
			return this.testDao.findMinScoreByCategoryAndUserEmail(category, userEmail);
		case "max":
			return this.testDao.findMaxScoreByCategoryAndUserEmail(category, userEmail);
		default:
			throw new RuntimeException("invalid action");
		}
		}catch(Exception e) {
			throw new NotFoundException("statistics not found");
		}	
	}
	
	@Override
	@Transactional(readOnly = true)
	public long findStatisticsByUserEmail(String action,String userEmail) {
		try {
		switch(action.toLowerCase()){
		case "count":
			return this.testDao.countByUserEmail(userEmail);
		case "average":
			return this.testDao.findAverageByUserEmail(userEmail);
		case "min":
			return this.testDao.findMinScoreByUserEmail(userEmail);
		case "max":
			return this.testDao.findMaxScoreByUserEmail(userEmail);
		default:
			throw new RuntimeException("invalid action");
		}
		}catch(Exception e) {
			throw new NotFoundException("statistics not found");
		}
	}

	// calculating a new date by adding days to the current date
	public Date getSpecificDate(int time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, time);
		Date date = cal.getTime();
		System.out.println(date);
		return date;
	}
	

}
