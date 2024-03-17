package uz.fazo.service;

import uz.fazo.payload.ProblemDto;

import java.util.List;

public interface ProblemService {


    List<ProblemDto> getAll();

    ProblemDto getById(long id);

    ProblemDto create(ProblemDto problemDto);

    void delete(long id);
}
