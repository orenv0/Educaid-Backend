package educaid.dal;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import educaid.data.TestRecord;

public interface TestRecordDao extends PagingAndSortingRepository<TestRecord, String> {

	public List<TestRecord> findAllByUserEmail(@Param("userEmail") String userEmail, Pageable pageable);

	public List<TestRecord> findAllByUserEmailAndCreatedTimestampGreaterThan(@Param("userEmail") String userEmail,
			@Param("createdTimestamp") Date createdTimestamp, Pageable pageable);

	public List<TestRecord> findAllByUserEmailAndCategory(@Param("userEmail") String userEmail,
			@Param("category") String category, Pageable pageable);

	public List<TestRecord> findAllByUserEmailAndCategoryAndCreatedTimestampGreaterThan(
			@Param("userEmail") String userEmail, @Param("category") String category,
			@Param("createdTimestamp") Date createdTimestamp, Pageable pageable);

	public long countByCategoryAndUserEmail(@Param("category") String category, @Param("userEmail") String userEmail);

	@Query("SELECT AVG(t.score) FROM TestRecord t WHERE t.category = ?1 AND t.userEmail = ?2")
	public long findAverageByCategoryAndUserEmail(@Param("category") String category,
			@Param("userEmail") String userEmail);
	
	@Query("SELECT MIN(t.score) FROM TestRecord t WHERE t.category = ?1 AND t.userEmail = ?2")
	public long findMinScoreByCategoryAndUserEmail(@Param("category") String category,
			@Param("userEmail") String userEmail);
	
	@Query("SELECT MAX(t.score) FROM TestRecord t WHERE t.category = ?1 AND t.userEmail = ?2")
	public long findMaxScoreByCategoryAndUserEmail(@Param("category") String category,
			@Param("userEmail") String userEmail);

	public long countByUserEmail(@Param("userEmail") String userEmail);

	@Query("SELECT AVG(t.score) FROM TestRecord t WHERE t.userEmail = ?1")
	public long findAverageByUserEmail(@Param("userEmail") String userEmail);

	@Query("SELECT MIN(t.score) FROM TestRecord t WHERE t.userEmail = ?1")
	public long findMinScoreByUserEmail(@Param("userEmail")String userEmail);

	@Query("SELECT MAX(t.score) FROM TestRecord t WHERE t.userEmail = ?1")
	public long findMaxScoreByUserEmail(@Param("userEmail")String userEmail);
}
