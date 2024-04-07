package uz.fazo.service;

import uz.fazo.payload.AccountingDto;

import java.util.List;

public interface AccountingService {
    List<AccountingDto> getAll();

    List<AccountingDto> getAllByUserId(int id);

    AccountingDto getById(long id);

    AccountingDto create(AccountingDto accountingDto);

    void update(long id, AccountingDto accountingDto);

    void delete(long id);
}
