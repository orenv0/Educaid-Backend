package educaid.data;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TEST_RECORDS")
public class TestRecord {
	private String id;
	private String userEmail;
	private int score;
	private String category;
	private Date createdTimestamp;
    private String[] userAnswers;
	
	public TestRecord() {
		super();
	}

	public TestRecord(String id, String userEmail, int score, String category, Date createdTimestamp,String[] userAnswers) {
		super();
		this.id = id;
		this.userEmail=userEmail;
		this.score = score;
		this.category = category;
		this.createdTimestamp = createdTimestamp;
		this.userAnswers = userAnswers;
		
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	
	public String[] getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(String[] userAnswers) {
		this.userAnswers = userAnswers;
	}

	@Override
	public String toString() {
		return "TestRecord [id=" + id + ", userEmail=" + userEmail + ", score=" + score + ", category=" + category
				+ ", createdTimestamp=" + createdTimestamp + ", userAnswers=" + userAnswers + "]";
	}

}
