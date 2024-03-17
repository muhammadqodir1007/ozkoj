package uz.fazo.service;

import uz.fazo.payload.MemberDto;

import java.util.List;

public interface MemberService {


    List<MemberDto> getAll();

    MemberDto getById(long id);

    MemberDto create(MemberDto memberDto);

    void delete(long id);

}