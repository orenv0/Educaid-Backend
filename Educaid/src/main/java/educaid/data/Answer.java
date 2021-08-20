package educaid.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"question"})
@Table(name = "ANSWERS")
public class Answer {	
private String id;	
private	String text;
private boolean correct;
private Question question;

public Answer() {
	super();
}



public Answer(String id, String text, boolean correct, Question question) {
	super();
	this.id = id;
	this.text = text;
	this.correct = correct;
	this.question = question;
}


@Id
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public boolean isCorrect() {
	return correct;
}
public void setCorrect(boolean correct) {
	this.correct = correct;
}
@ManyToOne(fetch = FetchType.LAZY)
public Question getQuestion() {
	return question;
}
public void setQuestion(Question question) {
	this.question = question;
}



@Override
public String toString() {
	return "Answer [id=" + id + ", text=" + text + ", correct=" + correct + ", question=" + question + "]";
}



}
