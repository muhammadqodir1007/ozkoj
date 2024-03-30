package uz.fazo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.fazo.entity.Problem;
import uz.fazo.payload.ProblemDto;
import uz.fazo.user.User;
import uz.fazo.user.UserRepository;

@Component
@AllArgsConstructor
public class ProblemMapperImpl implements ProblemMapper {

    UserRepository userRepository;

    @Override
    public ProblemDto problemToProblemDto(Problem problem) {
        if (problem == null) {
            return null;
        }

        return ProblemDto.builder().id(problem.getId())
                .fileName(problem.getFileName())
                .userId(problem.getUser().getId())
                .name(problem.getName())
                .build();

    }

    @Override
    public Problem problemDtoToProblem(ProblemDto problemDto) {
        if (problemDto == null) {
            return null;
        }
        User user = userRepository.findById(problemDto.getUserId()).orElseThrow(NullPointerException::new);

        return Problem.builder().id(problemDto.getId())
                .user(user)
                .fileName(problemDto.getFileName())
                .name(problemDto.getName())
                .build();

    }
}
