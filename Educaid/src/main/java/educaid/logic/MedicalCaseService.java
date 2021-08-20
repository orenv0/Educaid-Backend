package educaid.logic;

import java.util.List;

import educaid.data.MedicalCase;


public interface MedicalCaseService {
	public List<MedicalCase> getAll(int size,int page);
	public MedicalCase getSpecificCase(String caseName);
	public void bindInstructionToMedicalCase(String caseName, Integer instructionid);
	public void deleteAll();
}
