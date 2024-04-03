package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.fazo.entity.Member;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.MemberMapper;
import uz.fazo.payload.MemberDto;
import uz.fazo.repository.MemberRepository;
import uz.fazo.service.ExcelService;
import uz.fazo.service.MemberService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ExcelService excelService;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(memberMapper::memberToMemberDto).collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> getAllByUserId(int id) {
        return memberRepository.findAllByUserId(id).stream().map(memberMapper::memberToMemberDto).collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> createFromFile(MultipartFile file,int id) throws IOException {
        return excelService.createMembers(file,id);
    }

    @Override
    public byte[] exportMembersToExcel() throws IOException {
        List<Member> all = memberRepository.findAll();
        List<MemberDto> collect = all.stream().map(memberMapper::memberToMemberDto).collect(Collectors.toList());
        return excelService.exportMembersToExcel(collect);
    }

    @Override
    public MemberDto getById(long id) {
        return memberMapper.memberToMemberDto(memberRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public MemberDto update(long id, MemberDto memberDto) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        Member updatedMember = memberMapper.updateMemberFromDto(memberDto, existingMember);
        return memberMapper.memberToMemberDto(memberRepository.save(updatedMember));
    }

    @Override
    public MemberDto create(MemberDto memberDto) {
        Member member = memberMapper.memberDtoToMember(memberDto);
        Member save = memberRepository.save(member);
        return memberMapper.memberToMemberDto(save);
    }

    @Override
    public void delete(long id) {
        memberRepository.deleteById(id);

    }
}
