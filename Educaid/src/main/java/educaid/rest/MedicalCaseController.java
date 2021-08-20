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
import educaid.data.MedicalCase;
import educaid.logic.MedicalCaseService;


@RestController
public class MedicalCaseController {
	private MedicalCaseService medicalCaseService;

	@Autowired
	public void setMedicalCaseService(MedicalCaseService medicalCaseService) {
		this.medicalCaseService = medicalCaseService;
	}
	
	//get all cases	
	@RequestMapping(path = "/cases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalCase[] getAllCases(
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {

		return medicalCaseService.getAll(size, page).toArray((new MedicalCase[0]));

	}
	
	//get specific medical case
	@RequestMapping(path = "/cases/{caseName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalCase retrieveSpecificElement(
			@PathVariable("caseName") String caseName) {
		return this.medicalCaseService.getSpecificCase(caseName);
	}
	
	//bind existing instruction to medical case
	@RequestMapping(path = "/cases/{caseName}/instructions", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void bindExistingParentToExistingChild(@PathVariable("caseName") String caseName,
		 @RequestBody Map<String, Integer> instructionId) {
		this.medicalCaseService.bindInstructionToMedicalCase(caseName, instructionId.get("id"));
	}
	
	//deleteAllCases
	@RequestMapping(path = "/cases", method = RequestMethod.DELETE)
	public void deleteAllCases() {
			this.medicalCaseService.deleteAll();
		}
}
