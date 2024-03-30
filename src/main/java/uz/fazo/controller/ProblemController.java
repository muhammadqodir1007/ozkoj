package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.ProblemDto;
import uz.fazo.service.ProblemService;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    public ResponseEntity<List<ProblemDto>> getAllProblems() {
        List<ProblemDto> problems = problemService.getAll();
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ProblemDto>> getAllProblemsByUserId(@PathVariable int id) {
        List<ProblemDto> problems = problemService.getAllByUserId(id);
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> getProblemById(@PathVariable long id) {
        ProblemDto problem = problemService.getById(id);
        return ResponseEntity.ok(problem);
    }

    @PostMapping
    public ResponseEntity<ProblemDto> createProblem(@RequestBody ProblemDto problemDto) {
        ProblemDto createdProblem = problemService.create(problemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProblem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable long id) {
        problemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
