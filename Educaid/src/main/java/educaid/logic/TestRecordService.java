package educaid.logic;

import java.util.List;
import educaid.data.TestRecord;

public interface TestRecordService {
public TestRecord create(TestRecord test);
public List<TestRecord> getAllByUserAndCategoryAndDate(String userEmail, String category, String time, String sortBy,
		String sortOrder, int size, int page);
public List<TestRecord> getAllByUserAndCategory(String userEmail, String category, String sortBy, String sortOrder,
		int size, int page);
public List<TestRecord> getAllByUserAndDate(String userEmail, String time, String sortBy, String sortOrder, int size,
		int page);
public List<TestRecord> getAllByUser(String userEmail, String sortBy, String sortOrder, int size, int page);
public long findStatisticsByCategoryAndUserEmail(String action,String category, String userEmail);
public long findStatisticsByUserEmail(String action, String userEmail);

}
