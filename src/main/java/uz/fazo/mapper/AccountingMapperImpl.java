package uz.fazo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.fazo.entity.Accounting;
import uz.fazo.payload.AccountingDto;
import uz.fazo.user.User;
import uz.fazo.user.UserRepository;

@Component
@AllArgsConstructor
public class AccountingMapperImpl implements AccountingMapper {

    private final UserRepository userRepository;

    @Override
    public AccountingDto accountingToAccountingDto(Accounting accounting) {
        if (accounting == null) {
            return null;
        }

        return AccountingDto.builder()
                .id(accounting.getId())
                .number(accounting.getNumber())
                .userId(accounting.getUser().getId())
                .type(accounting.getType())
                .year(accounting.getYear())
                .period(accounting.getPeriod())
                .address(accounting.getAddress())
                .file1(accounting.getFile1())
                .file2(accounting.getFile2())
                .file3(accounting.getFile3())
                .file4(accounting.getFile4())
                .build();
    }

    @Override
    public Accounting accountingDtoToAccounting(AccountingDto accountingDto) {
        if (accountingDto == null) {
            return null;
        }
        User user = userRepository.findById(accountingDto.getUserId()).orElseThrow(NullPointerException::new);
        return Accounting.builder()
                .id(accountingDto.getId())
                .number(accountingDto.getNumber())
                .user(user)
                .type(accountingDto.getType())
                .year(accountingDto.getYear())
                .period(accountingDto.getPeriod())
                .address(accountingDto.getAddress())
                .file1(accountingDto.getFile1())
                .file2(accountingDto.getFile2())
                .file3(accountingDto.getFile3())
                .file4(accountingDto.getFile4())
                .build();
    }

    @Override
    public Accounting updateAccountingFromDto(AccountingDto accountingDto, Accounting accounting) {
        if (accountingDto == null || accounting == null) {
            return null;
        }

        if (accountingDto.getNumber() != null) {
            accounting.setNumber(accountingDto.getNumber());
        }
        if (accountingDto.getUserId() != 0) {
            User user = userRepository.findById(accountingDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            accounting.setUser(user);
        }
        if (accountingDto.getType() != null) {
            accounting.setType(accountingDto.getType());
        }
        if (accountingDto.getYear() != null) {
            accounting.setYear(accountingDto.getYear());
        }
        if (accountingDto.getPeriod() != null) {
            accounting.setPeriod(accountingDto.getPeriod());
        }
        if (accountingDto.getAddress() != null) {
            accounting.setAddress(accountingDto.getAddress());
        }
        if (accountingDto.getFile1() != null) {
            accounting.setFile1(accountingDto.getFile1());
        }
        if (accountingDto.getFile2() != null) {
            accounting.setFile2(accountingDto.getFile2());
        }
        if (accountingDto.getFile3() != null) {
            accounting.setFile3(accountingDto.getFile3());
        }
        if (accountingDto.getFile4() != null) {
            accounting.setFile4(accountingDto.getFile4());
        }

        return accounting;
    }
}