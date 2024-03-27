package uz.fazo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;
import uz.fazo.service.ExcelService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/excel")
public class ExcelController {

    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<List<ClientDto>> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<ClientDto> clientDtos = excelService.create(file);

        return ResponseEntity.ok(clientDtos);
    }
}

