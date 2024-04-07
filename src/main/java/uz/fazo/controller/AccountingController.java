package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.AccountingDto;
import uz.fazo.service.AccountingService;

import java.util.List;

@RestController
@RequestMapping("/api/accounting")
public class AccountingController {

    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @GetMapping
    public ResponseEntity<List<AccountingDto>> getAllAccountings() {
        List<AccountingDto> accountings = accountingService.getAll();
        return ResponseEntity.ok(accountings);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AccountingDto>> getAllAccountingsByUserId(@PathVariable int id) {
        List<AccountingDto> accountings = accountingService.getAllByUserId(id);
        return ResponseEntity.ok(accountings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountingDto> getAccountingById(@PathVariable long id) {
        AccountingDto accounting = accountingService.getById(id);
        return ResponseEntity.ok(accounting);
    }

    @PostMapping
    public ResponseEntity<AccountingDto> createAccounting(@RequestBody AccountingDto accountingDto) {
        AccountingDto createdAccounting = accountingService.create(accountingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccounting);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateAccounting(@PathVariable long id, @RequestBody AccountingDto accountingDto) {
        accountingService.update(id, accountingDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccounting(@PathVariable long id) {
        accountingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
