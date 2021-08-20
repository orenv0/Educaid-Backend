package educaid.logic.db;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import educaid.dal.AnswerDao;
import educaid.dal.QuestionDao;
import educaid.data.Answer;
import educaid.data.Question;
import educaid.logic.NotFoundException;
import educaid.logic.QuestionService;

@Service
public class QuestionServiceDb implements QuestionService  {
	private QuestionDao questionDao;
	private AnswerDao answerDao;
	
	@Autowired
	public QuestionServiceDb(QuestionDao questionDao,AnswerDao answerDao) {
		this.questionDao = questionDao;
		this.answerDao = answerDao;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Question> getAll(int size, int page) {
		// TODO Auto-generated method stub
		return questionDao.findAll(PageRequest.of(page, size, Direction.ASC, "id")).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Question getSpecificQuestion(Integer id) {
		// TODO Auto-generated method stub
		return questionDao.findById(id).orElseThrow(() -> new NotFoundException("No such question"));
	}
	@Override
	public void bindAnswerToQuestion(Integer questionId, String answerId) {
		// TODO Auto-generated method stub
		Question question = this.questionDao.findById(questionId)
				.orElseThrow(() -> new NotFoundException("no question with the id: " + questionId));

		Answer answer = this.answerDao.findById(answerId)
				.orElseThrow(() -> new NotFoundException("no answer with the id: " + answerId));

		question.addAnswer(answer);
		this.questionDao.save(question);
		
	}
	@Override
	public Question getByCategoryAndDiffculty(String category,int difficulty) {
		// TODO Auto-generated method stub
		Random rand = new Random(); 
		List<Question> questions = questionDao.findAllByCategoryAndDifficulty(category,difficulty);
		int randNum = rand.nextInt(questions.size());
		Question selected = questions.get(randNum);
		return selected;
	}


}
