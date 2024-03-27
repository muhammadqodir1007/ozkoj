package uz.fazo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.fazo.entity.Member;
import uz.fazo.payload.MemberDto;

@Mapper
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);

    Member memberDtoToMember(MemberDto memberDto);
    Member updateMemberFromDto(MemberDto memberDto, @MappingTarget Member member);
}
