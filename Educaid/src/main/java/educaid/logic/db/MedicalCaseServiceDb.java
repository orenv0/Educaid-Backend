package educaid.logic.db;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import educaid.logic.NotFoundException;
import educaid.dal.InstructionDao;
import educaid.dal.MedicalCaseDao;
import educaid.data.Instruction;
import educaid.data.MedicalCase;
import educaid.logic.MedicalCaseService;

@Service
public class MedicalCaseServiceDb implements MedicalCaseService  {
	private MedicalCaseDao medicalCaseDao;
	private InstructionDao instructionDao;
	
	@Autowired
	public MedicalCaseServiceDb(MedicalCaseDao medicalCaseDao,InstructionDao instructionDao) {
		this.medicalCaseDao = medicalCaseDao;
		this.instructionDao = instructionDao;
	}


	@Override
	@Transactional(readOnly = true)
	public List<MedicalCase> getAll(int size, int page) {
		// TODO Auto-generated method stub
		return this.medicalCaseDao.findAll(PageRequest.of(page, size, Direction.ASC, "caseName")).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public MedicalCase getSpecificCase(String caseName) {
		// TODO Auto-generated method stub
		return medicalCaseDao.findById(caseName).orElseThrow(() -> new NotFoundException("No such medical case"));
	}


	@Override
	public void bindInstructionToMedicalCase(String caseName, Integer instructionId) {
		// TODO Auto-generated method stub
		MedicalCase medicalCase = this.medicalCaseDao.findById(caseName)
				.orElseThrow(() -> new NotFoundException("no case with the name: " + caseName));

		Instruction instruction = this.instructionDao.findById(instructionId)
				.orElseThrow(() -> new NotFoundException("no instruction with id: " + instructionId));

		medicalCase.addInstruction(instruction);
		this.medicalCaseDao.save(medicalCase);
	}


	@Override
	public void deleteAll() {
		this.instructionDao.deleteAll();
		this.medicalCaseDao.deleteAll();
		
	}

}
