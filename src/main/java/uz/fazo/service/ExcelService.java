package uz.fazo.service;

import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.ClientDto;
import uz.fazo.payload.MemberDto;

import java.io.IOException;
import java.util.List;

public interface ExcelService {


    List<ClientDto> createClients(MultipartFile file, int id) throws IOException;

    List<MemberDto> createMembers(MultipartFile file, int id) throws IOException;

    byte[] exportMembersToExcel(List<MemberDto> members) throws IOException;

    byte[] exportClientsToExcel(List<ClientDto> members) throws IOException;
}
