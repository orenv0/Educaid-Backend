package educaid.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import educaid.data.MedicalCase;



public interface MedicalCaseDao extends PagingAndSortingRepository<MedicalCase, String> {

}
