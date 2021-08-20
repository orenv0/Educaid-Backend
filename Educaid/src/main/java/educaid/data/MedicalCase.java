package educaid.data;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "MEDICAL_CASES")
public class MedicalCase {
	private String caseName;
	private String description;
	private String category;
	private String videoUrl;
	private List<Instruction> instructions;
	
	

	public MedicalCase() {
		super();
		this.instructions = new ArrayList<>();
	}

	public MedicalCase(String caseName,String description, String category, String videoUrl) {
		super();
		this.caseName = caseName;
		this.description = description;
		this.category = category;
		this.videoUrl = videoUrl;
		this.instructions = new ArrayList<>();

	}

	@Id
	@Column(name="caseName")
	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
    @Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	@OneToMany(mappedBy = "medicalCase",fetch = FetchType.LAZY)
	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	public void addInstruction (Instruction instruction) {
		this.instructions.add(instruction);
		instruction.setMedicalCase(this);
	}

	@Override
	public String toString() {
		return "MedicalCase [caseName=" + caseName + ", description=" + description + ", category=" + category
				+ ", videoUrl=" + videoUrl + ", instructions=" + instructions + "]";
	}



}
