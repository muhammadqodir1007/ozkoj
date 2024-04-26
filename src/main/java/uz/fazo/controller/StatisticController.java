package uz.fazo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.StatisticDto;
import uz.fazo.service.StatisticService;
import uz.fazo.service.impl.ExcelServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    private final StatisticService statisticService;
    private final ExcelServiceImpl excelService;

    public StatisticController(StatisticService statisticService, ExcelServiceImpl excelService) {
        this.statisticService = statisticService;
        this.excelService = excelService;
    }

    @GetMapping
    public ResponseEntity<List<StatisticDto>> getAllStatistics() {
        List<StatisticDto> statistics = statisticService.getAll();
        return ResponseEntity.ok(statistics);
    }


    @GetMapping("/merge-and-download")
    public ResponseEntity<Resource> mergeAndDownloadStatistics() throws IOException {
        List<StatisticDto> statistics = statisticService.getAll();
        List<String> filepaths = statistics.stream().map(StatisticDto::getLink).collect(Collectors.toList());
        String targetFilePath = "src/main/resources/static/images/merged_statistics.xlsx"; // You may want to use a more dynamic path or a temporary file

        // Merge the files into one
        excelService.mergeExcelFiles(filepaths, targetFilePath);

        // Set up the file for download
        File file = new File(targetFilePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .contentLength(file.length())
                .body(resource);
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
        System.out.println("first try 1");
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
