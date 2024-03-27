package uz.fazo.mapper;

import org.springframework.stereotype.Component;
import uz.fazo.entity.Member;
import uz.fazo.payload.MemberDto;

@Component
public class MemberMapperImpl implements MemberMapper {


    @Override
    public MemberDto memberToMemberDto(Member member) {

        if (member == null) {
            return null;
        }
        return MemberDto.builder().id(member.getId())
                .address(member.getAddress())
                .fullName(member.getFullName())
                .birthDate(member.getBirthDate())
                .groupNumber(member.getGroupNumber())
                .state(member.getState())
                .passportNumber(member.getPassportNumber())
                .phoneNumber(member.getPhoneNumber())
                .passportSeries(member.getPassportSeries())
                .build();


    }

    @Override
    public Member memberDtoToMember(MemberDto memberDto) {
        if (memberDto == null) {
            return null;
        }

        return Member.builder().id(memberDto.getId())
                .address(memberDto.getAddress())
                .fullName(memberDto.getFullName())
                .birthDate(memberDto.getBirthDate())
                .groupNumber(memberDto.getGroupNumber())
                .state(memberDto.getState())
                .passportNumber(memberDto.getPassportNumber())
                .phoneNumber(memberDto.getPhoneNumber())
                .passportSeries(memberDto.getPassportSeries())
                .build();
    }

    @Override
    public Member updateMemberFromDto(MemberDto memberDto, Member member) {
        if (memberDto == null || member == null) {
            return null;
        }

        if (memberDto.getFullName() != null) {
            member.setFullName(memberDto.getFullName());
        }
        if (memberDto.getAddress() != null) {
            member.setAddress(memberDto.getAddress());
        }
        if (memberDto.getBirthDate() != null) {
            member.setBirthDate(memberDto.getBirthDate());
        }
        if (memberDto.getState() != null) {
            member.setState(memberDto.getState());
        }
        if (memberDto.getPassportSeries() != null) {
            member.setPassportSeries(memberDto.getPassportSeries());
        }
        if (memberDto.getPassportNumber() != null) {
            member.setPassportNumber(memberDto.getPassportNumber());
        }
        if (memberDto.getPhoneNumber() != null) {
            member.setPhoneNumber(memberDto.getPhoneNumber());
        }
        member.setGroupNumber(memberDto.getGroupNumber());

        return member;
    }

}
