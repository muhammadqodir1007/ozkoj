package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Member;
import uz.fazo.payload.MemberDto;

@Mapper
public interface MemberMapper {

    MemberDto memberToMemberDto(Member member);

    Member memberDtoToMember(MemberDto memberDto);
}
