package uz.fazo.mapper;

import org.springframework.stereotype.Component;
import uz.fazo.entity.Problem;
import uz.fazo.payload.ProblemDto;

@Component
public class ProblemMapperImpl implements ProblemMapper {
    @Override
    public ProblemDto problemToProblemDto(Problem problem) {
        if (problem == null) {
            return null;
        }

        return ProblemDto.builder().id(problem.getId())
                .fileName(problem.getFileName())
                .name(problem.getName())
                .build();

    }

    @Override
    public Problem problemDtoToProblem(ProblemDto problemDto) {
        if (problemDto == null) {
            return null;
        }

        return Problem.builder().id(problemDto.getId())
                .fileName(problemDto.getFileName())
                .name(problemDto.getName())
                .build();

    }
}
