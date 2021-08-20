package educaid.dal;

import org.springframework.data.repository.PagingAndSortingRepository;
import educaid.data.Answer;


public interface AnswerDao extends PagingAndSortingRepository<Answer, String>{

}
