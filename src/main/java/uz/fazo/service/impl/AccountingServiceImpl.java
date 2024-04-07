package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Accounting;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.AccountingMapper;
import uz.fazo.payload.AccountingDto;
import uz.fazo.repository.AccountingRepository;
import uz.fazo.service.AccountingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountingServiceImpl implements AccountingService {

    private final AccountingRepository accountingRepository;
    private final AccountingMapper accountingMapper;

    @Override
    public List<AccountingDto> getAll() {
        return accountingRepository.findAll().stream()
                .map(accountingMapper::accountingToAccountingDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountingDto> getAllByUserId(int id) {
        return accountingRepository.findAllByUserId(id).stream()
                .map(accountingMapper::accountingToAccountingDto)
                .collect(Collectors.toList());
    }


    @Override
    public AccountingDto getById(long id) {
        Accounting accounting = accountingRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        return accountingMapper.accountingToAccountingDto(accounting);
    }


    @Override
    public AccountingDto create(AccountingDto accountingDto) {
        Accounting accounting = accountingMapper.accountingDtoToAccounting(accountingDto);
        Accounting savedAccounting = accountingRepository.save(accounting);
        return accountingMapper.accountingToAccountingDto(savedAccounting);
    }

    @Override
    public void update(long id, AccountingDto accountingDto) {
        Accounting accounting = accountingRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        accounting = accountingMapper.updateAccountingFromDto(accountingDto, accounting);
        accountingRepository.save(accounting);
    }

    @Override
    public void delete(long id) {
        accountingRepository.deleteById(id);
    }
}
