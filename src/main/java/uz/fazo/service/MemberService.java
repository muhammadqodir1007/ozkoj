package uz.fazo.service;

import org.springframework.web.multipart.MultipartFile;
import uz.fazo.payload.MemberDto;

import java.io.IOException;
import java.util.List;

public interface MemberService {


    List<MemberDto> getAll();

    List<MemberDto> getAllByUserId(int id);

    List<MemberDto> createFromFile(MultipartFile file,int id) throws IOException;

    byte[] exportMembersToExcel() throws IOException;

    MemberDto getById(long id);

    MemberDto update(long id, MemberDto memberDto);

    MemberDto create(MemberDto memberDto);

    void delete(long id);


}
