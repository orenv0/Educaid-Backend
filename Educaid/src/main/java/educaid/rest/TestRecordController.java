package educaid.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import educaid.data.TestRecord;
import educaid.logic.TestRecordService;

@RestController
public class TestRecordController {
	private TestRecordService testService;

	@Autowired
	public void setTestService(TestRecordService testService) {
		this.testService = testService;
	}

	@RequestMapping(path = "/records", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TestRecord createTest(@RequestBody TestRecord test) {
		return this.testService.create(test);
	}

	
	@RequestMapping(path = "/records/byUserAndCategoryAndTime/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TestRecord[] getAllRecordsByUserAndCategoryAndTime(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "createdTimestamp") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "category", required = true) String category,
			@RequestParam(name = "time", required = true) String time) {
		
		return this.testService.getAllByUserAndCategoryAndDate(userEmail,category,time,sortBy, sortOrder, size, page)
				.toArray(new TestRecord[0]);
	}
	@RequestMapping(path = "/records/byUserAndCategory/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TestRecord[] getAllRecordsByUserAndCategory(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "createdTimestamp") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "category", required = true) String category
			) {
		
		return this.testService.getAllByUserAndCategory(userEmail,category,sortBy, sortOrder, size, page)
				.toArray(new TestRecord[0]);
	}
	@RequestMapping(path = "/records/byUserAndTime/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TestRecord[] getAllRecordsByUserAndTime(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "createdTimestamp") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(name = "time", required = true) String time) {
		
		return this.testService.getAllByUserAndDate(userEmail,time,sortBy, sortOrder, size, page)
				.toArray(new TestRecord[0]);
	}
	@RequestMapping(path = "/records/byUser/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TestRecord[] getAllRecordsByUser(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "sortBy", required = false, defaultValue = "createdTimestamp") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "ASC") String sortOrder
			) {
		
		return this.testService.getAllByUser(userEmail,sortBy, sortOrder, size, page)
				.toArray(new TestRecord[0]);
	}
	@RequestMapping(path = "/records/statisticsByCategoryAndUser/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public long getStatisticsByCategoryAndUserEmail(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "category", required = true) String category,
			@RequestParam(name = "action", required = true) String action
		
			) {
		return this.testService.findStatisticsByCategoryAndUserEmail(action,category, userEmail);
	}
	@RequestMapping(path = "/records/statisticsByUser/{userEmail}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public long getStatisticsByUserEmail(@PathVariable("userEmail") String userEmail,
			@RequestParam(name = "action", required = true) String action
		
			) {
		return this.testService.findStatisticsByUserEmail(action, userEmail);
	}

	
}
