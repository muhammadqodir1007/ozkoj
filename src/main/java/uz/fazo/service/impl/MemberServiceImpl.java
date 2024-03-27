package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Member;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.MemberMapper;
import uz.fazo.payload.MemberDto;
import uz.fazo.repository.MemberRepository;
import uz.fazo.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream().map(memberMapper::memberToMemberDto).collect(Collectors.toList());
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
