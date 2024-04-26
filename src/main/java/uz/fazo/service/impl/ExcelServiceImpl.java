package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.entity.Client;
import uz.fazo.entity.Member;
import uz.fazo.mapper.ClientMapper;
import uz.fazo.mapper.MemberMapper;
import uz.fazo.payload.ClientDto;
import uz.fazo.payload.MemberDto;
import uz.fazo.repository.ClientRepository;
import uz.fazo.repository.MemberRepository;
import uz.fazo.service.ExcelService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final ClientRepository clientRepository;
    private final MemberRepository memberRepository;
    private final ClientMapper clientMapper;
    private final MemberMapper memberMapper;

    @Override
    public List<ClientDto> createClients(MultipartFile file, int id) throws IOException {
        List<ClientDto> clients = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip the header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isEmptyRow(row)) {
                    ClientDto clientDto = mapRowToClientDto(row);
                    if (clientDto != null) {
                        clientDto.setUserId(id);
                        Client save = clientRepository.save(clientMapper.clientDtoToClient(clientDto));
                        clients.add(clientMapper.clientToClientDto(save));
                    }
                }
            }
        }
        return clients;
    }

    @Override
    public List<MemberDto> createMembers(MultipartFile file, int id) throws IOException {
        List<MemberDto> members = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isEmptyRow(row)) {
                    MemberDto memberDto = mapRowToMemberDto(row);
                    if (memberDto != null) {
                        memberDto.setUserId(id);
                        Member save = memberRepository.save(memberMapper.memberDtoToMember(memberDto));
                        members.add(memberMapper.memberToMemberDto(save));
                    }
                }
            }
        }
        return members;
    }

    private boolean isEmptyRow(Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() != CellType.BLANK) {
                return true;
            }
        }
        return false;
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

    private MemberDto mapRowToMemberDto(Row row) {
        try {
            return MemberDto.builder()
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
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if the numeric cell contains a date value
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            default:
                return null;
        }
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

    @Override
    public byte[] exportMembersToExcel(List<MemberDto> members) throws IOException {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet
        Sheet sheet = workbook.createSheet("Members");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Full Name", "Birth Date", "Group Number", "Address", "Passport Series",
                "Passport Number", "Phone Number", "State"};

        // Write the header
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Write data rows
        int rowNum = 1;
        for (MemberDto member : members) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(member.getFullName());
            row.createCell(1).setCellValue(member.getBirthDate());
            row.createCell(2).setCellValue(member.getGroupNumber());
            row.createCell(3).setCellValue(member.getAddress());
            row.createCell(4).setCellValue(member.getPassportSeries());
            row.createCell(5).setCellValue(member.getPassportNumber());
            row.createCell(6).setCellValue(member.getPhoneNumber());
            row.createCell(7).setCellValue(member.getState());
        }

        // Write the workbook to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] excelBytes = outputStream.toByteArray();

        // Close the workbook and output stream
        outputStream.close();
        workbook.close();

        return excelBytes;
    }


    @Override
    public byte[] exportClientsToExcel(List<ClientDto> members) throws IOException {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet
        Sheet sheet = workbook.createSheet("Members");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Full Name", "Birth Date", "Group Number", "Address", "Passport Series",
                "Passport Number", "Phone Number", "State"};

        // Write the header
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Write data rows
        int rowNum = 1;
        for (ClientDto member : members) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(member.getFullName());
            row.createCell(1).setCellValue(member.getBirthDate());
            row.createCell(2).setCellValue(member.getGroupNumber());
            row.createCell(3).setCellValue(member.getAddress());
            row.createCell(4).setCellValue(member.getPassportSeries());
            row.createCell(5).setCellValue(member.getPassportNumber());
            row.createCell(6).setCellValue(member.getPhoneNumber());
            row.createCell(7).setCellValue(member.getState());
        }

        // Write the workbook to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] excelBytes = outputStream.toByteArray();

        // Close the workbook and output stream
        outputStream.close();
        workbook.close();

        return excelBytes;
    }

    public void mergeExcelFiles(List<String> filepaths, String targetFilePath) throws IOException {
        Workbook targetWorkbook = new XSSFWorkbook(); // Create a new Excel workbook
        for (int i = 0; i < filepaths.size(); i++) {

            String filepath = "src/main/resources/static/images/" + filepaths.get(i);
            FileInputStream inputStream = new FileInputStream(new File(filepath));
            Workbook sourceWorkbook = new XSSFWorkbook(inputStream);

            // Iterate through each sheet in the source workbook
            for (int j = 0; j < sourceWorkbook.getNumberOfSheets(); j++) {
                Sheet sourceSheet = sourceWorkbook.getSheetAt(j);
                Sheet targetSheet = targetWorkbook.createSheet("Sheet " + (i + 1) + "-" + (j + 1)); // Create a new sheet in the target workbook

                // Iterate through each row in the source sheet and copy to the target sheet
                for (int k = 0; k <= sourceSheet.getLastRowNum(); k++) {
                    Row sourceRow = sourceSheet.getRow(k);
                    Row targetRow = targetSheet.createRow(k);
                    if (sourceRow != null) {
                        // Iterate through each cell in the source row and copy to the target row
                        for (int l = 0; l < sourceRow.getLastCellNum(); l++) {
                            Cell sourceCell = sourceRow.getCell(l);
                            Cell targetCell = targetRow.createCell(l);
                            if (sourceCell != null) {
                                switch (sourceCell.getCellType()) {
                                    case STRING:
                                        targetCell.setCellValue(sourceCell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        targetCell.setCellValue(sourceCell.getNumericCellValue());
                                        break;
                                    case BOOLEAN:
                                        targetCell.setCellValue(sourceCell.getBooleanCellValue());
                                        break;
                                    case FORMULA:
                                        targetCell.setCellFormula(sourceCell.getCellFormula());
                                        break;
                                    default:
                                        targetCell.setCellValue(sourceCell.toString());
                                }
                            }
                        }
                    }
                }
            }
            inputStream.close();
        }

        // Write the target workbook to a file
        FileOutputStream outputStream = new FileOutputStream(targetFilePath);
        targetWorkbook.write(outputStream);
        targetWorkbook.close();
        outputStream.close();
    }


}
