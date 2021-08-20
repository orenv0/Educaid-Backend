package educaid.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"medicalCase"})
@Table(name = "INSTRUCTIONS")
public class Instruction {
	private Integer id;
	private String text;
	private String voiceUrl;
	private MedicalCase medicalCase;
	

	public Instruction() {
		super();
	}
	public Instruction(Integer id,String text, String voiceUrl ,MedicalCase medicalCase) {
		super();
		this.text = text;
		this.medicalCase = medicalCase;
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
	
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public MedicalCase getMedicalCase() {
		return medicalCase;
	}
	public void setMedicalCase(MedicalCase medicalCase) {
		this.medicalCase = medicalCase;
	}
	@Override
	public String toString() {
		return "Instruction [id=" + id + ", text=" + text + ", voiceUrl=" + voiceUrl + ", medicalCase=" + medicalCase
				+ "]";
	}
	

}
