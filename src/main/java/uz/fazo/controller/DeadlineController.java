package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.entity.Deadline;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.repository.DeadlineRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/deadline")
public class DeadlineController {

    private final DeadlineRepository deadlineRepository;


    public DeadlineController(DeadlineRepository deadlineRepository) {
        this.deadlineRepository = deadlineRepository;
    }

    @GetMapping
    public ResponseEntity<List<Deadline>> getDeadline() {
        List<Deadline> all = deadlineRepository.findAll();
        return ResponseEntity.ok(all);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Deadline> updateDeadline(@PathVariable Long id, @RequestParam("localDateTime") LocalDateTime localDateTime) {
        Deadline deadline = deadlineRepository.findById(id).orElseThrow(NotFoundException::new);
        deadline.setLocalDateTime(localDateTime);
        Deadline save = deadlineRepository.save(deadline);
        return ResponseEntity.ok(save);

    }

    @PostMapping
    public ResponseEntity<Deadline> create(@RequestBody Deadline deadline) {
        if (deadlineRepository.findAll().size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deadline);
        } else {

            Deadline save = deadlineRepository.save(deadline);
            Deadline save1 = deadlineRepository.save(save);
            return ResponseEntity.status(HttpStatus.CREATED).body(save1);
        }

    }

    @GetMapping("/{id}")
    public Boolean isRevoked(@PathVariable Long id) {
        return deadlineRepository.findById(id).orElseThrow(NotFoundException::new).getLocalDateTime().isAfter(LocalDateTime.now());
    }


}
