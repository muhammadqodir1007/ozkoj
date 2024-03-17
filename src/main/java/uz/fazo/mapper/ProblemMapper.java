package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Problem;
import uz.fazo.payload.ProblemDto;

@Mapper
public interface ProblemMapper {


    ProblemDto problemToProblemDto(Problem problem);

    Problem problemDtoToProblem(ProblemDto problemDto);
}
