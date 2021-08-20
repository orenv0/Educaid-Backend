package educaid.dal;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import educaid.data.Question;

public interface QuestionDao extends PagingAndSortingRepository<Question, Integer> {

	public List<Question> findAllByCategoryAndDifficulty(@Param("category") String category,@Param("difficulty") int difficulty);

}
