package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Accounting;
import uz.fazo.payload.AccountingDto;
@Mapper
public interface AccountingMapper {
    AccountingDto accountingToAccountingDto(Accounting accounting);

    Accounting accountingDtoToAccounting(AccountingDto accountingDto);

    Accounting updateAccountingFromDto(AccountingDto accountingDto, Accounting accounting);
}
