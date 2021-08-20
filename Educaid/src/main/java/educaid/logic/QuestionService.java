package educaid.logic;

import java.util.List;


import educaid.data.Question;

public interface QuestionService {
	public List<Question> getAll(int size,int page);
	public Question getSpecificQuestion(Integer id);
	public void bindAnswerToQuestion(Integer questionId, String answerId);
	public Question getByCategoryAndDiffculty(String category,int difficulty);
}
