package educaid.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import educaid.data.Question;
import educaid.logic.QuestionService;

@RestController
public class QuestionController {
	private QuestionService questionService;

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	// get all questions
	@RequestMapping(path = "/questions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Question[] getAllQuestions(@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {

		return questionService.getAll(size, page).toArray((new Question[0]));

	}
	// get all questions by difficulty
	@RequestMapping(path = "/questions/byCategoryAndDifficulty", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Question getAllQuestionsByDiffculty(@RequestParam(name = "category", required = true) String category,
			@RequestParam(name = "difficulty", required = true, defaultValue = "1") int difficulty) {

		return this.questionService.getByCategoryAndDiffculty(category,difficulty);

	}

	// get specific question
	@RequestMapping(path = "/questions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Question retrieveSpecificQuestion(@PathVariable("id") Integer id) {
		return this.questionService.getSpecificQuestion(id);
	}

	// bind existing instruction to medical case
	@RequestMapping(path = "questions/{questionId}/answers", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void bindExistingQuestionToExistingAnswer(@PathVariable("questionId") Integer questionId,
			@RequestBody Map<String, String> answerId) {
		this.questionService.bindAnswerToQuestion(questionId, answerId.get("id"));
	}

}
