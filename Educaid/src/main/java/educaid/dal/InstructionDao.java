package educaid.dal;

import org.springframework.data.repository.PagingAndSortingRepository;
import educaid.data.Instruction;

public interface InstructionDao extends PagingAndSortingRepository<Instruction, Integer> {

}
