package educaid.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question {
private Integer id;	
private String text;
private String category;
private int difficulty;
private Set<Answer> answers;


public Question() {
	super();
	this.answers = new HashSet<>();
}


public Question(Integer id, String text, String category,int difficulty) {
	super();
	this.id = id;
	this.text = text;
	this.category = category;
	this.difficulty = difficulty;
	this.answers = new HashSet<>();
}

@Id
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

public int getDifficulty() {
	return difficulty;
}


public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
}

@OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
public Set<Answer> getAnswers() {
	return answers;
}
public void setAnswers(Set<Answer> answers) {
	this.answers = answers;
}
public void addAnswer (Answer answer) {
	this.answers.add(answer);
	answer.setQuestion(this);
}


@Override
public String toString() {
	return "Question [id=" + id + ", text=" + text + ", category=" + category + ", difficulty=" + difficulty
			+ ", answers=" + answers + "]";
}




}
