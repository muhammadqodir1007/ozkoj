package uz.fazo.service;


import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;

import java.io.IOException;
import java.util.List;

public interface ClientService {


    List<ClientDto> getAll();

    List<ClientDto> createFromFile(MultipartFile file) throws IOException;


    byte[] exportMembersToExcel() throws IOException;

    ClientDto getById(long id);

    ClientDto create(ClientDto memberDto);

    ClientDto update(long id, ClientDto clientDto);

    void delete(long id);

}
