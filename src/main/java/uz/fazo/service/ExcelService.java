package uz.fazo.service;

import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;

import java.io.IOException;
import java.util.List;

public interface ExcelService {


    List<ClientDto> create(MultipartFile fileInputStream) throws IOException;


}
