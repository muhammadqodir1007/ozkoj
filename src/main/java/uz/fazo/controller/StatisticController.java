package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.StatisticDto;
import uz.fazo.service.StatisticService;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public ResponseEntity<List<StatisticDto>> getAllStatistics() {
        List<StatisticDto> statistics = statisticService.getAll();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatisticDto> getStatisticById(@PathVariable long id) {
        StatisticDto statistic = statisticService.getById(id);
        return ResponseEntity.ok(statistic);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<StatisticDto>> getAllStatisticsByUserId(@PathVariable int id) {
        List<StatisticDto> statistics = statisticService.getAllByUserId(id);
        return ResponseEntity.ok(statistics);
    }

    @PostMapping
    public ResponseEntity<StatisticDto> createStatistic(@RequestBody StatisticDto statisticDto) {
        StatisticDto createdStatistic = statisticService.create(statisticDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatistic);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StatisticDto> updateStatistic(@PathVariable long id, @RequestBody StatisticDto statisticDto) {
        StatisticDto updatedStatistic = statisticService.update(id, statisticDto);
        return ResponseEntity.ok(updatedStatistic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable long id) {
        statisticService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
