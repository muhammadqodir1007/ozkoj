package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Problem;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.ProblemMapper;
import uz.fazo.payload.ProblemDto;
import uz.fazo.repository.ProblemRepository;
import uz.fazo.service.ProblemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    @Override
    public List<ProblemDto> getAll() {
        return problemRepository.findAll().stream().map(problemMapper::problemToProblemDto).collect(Collectors.toList());
    }

    @Override
    public List<ProblemDto> getAllByUserId(int id) {
        return problemRepository.findAllByUserId(id).stream().map(problemMapper::problemToProblemDto).collect(Collectors.toList());
    }

    @Override
    public ProblemDto getById(long id) {
        return problemMapper.problemToProblemDto(problemRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public ProblemDto create(ProblemDto problemDto) {
        if (problemDto == null) {
            throw new IllegalArgumentException("ProblemDto cannot be null");
        }
        Problem problem = problemMapper.problemDtoToProblem(problemDto);
        Problem savedProblem = problemRepository.save(problem);
        return problemMapper.problemToProblemDto(savedProblem);
    }

    @Override
    public void delete(long id) {
        problemRepository.deleteById(id);
    }
}
