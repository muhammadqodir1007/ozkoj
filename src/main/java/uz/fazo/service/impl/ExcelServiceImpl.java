package uz.fazo.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;
import uz.fazo.service.ExcelService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public List<ClientDto> create(MultipartFile file) throws IOException {
        List<ClientDto> clients = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ClientDto clientDto = mapRowToClientDto(row);
                if (clientDto != null) {
                    clients.add(clientDto);
                }
            }
        }
        return clients;
    }

    private ClientDto mapRowToClientDto(Row row) {
        try {
            return ClientDto.builder()
                    .fullName(getCellValueAsString(row.getCell(0)))
                    .birthDate(getCellValueAsString(row.getCell(1)))
                    .groupNumber(getCellValueAsInt(row.getCell(2)))
                    .address(getCellValueAsString(row.getCell(3)))
                    .passportSeries(getCellValueAsString(row.getCell(4)))
                    .passportNumber(getCellValueAsString(row.getCell(5)))
                    .phoneNumber(getCellValueAsString(row.getCell(6)))
                    .state(getCellValueAsString(row.getCell(7)))
                    .build();
        } catch (Exception e) {
            // Handle any errors during mapping
            e.printStackTrace();
            return null;
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            default -> null;
        };
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }


}

