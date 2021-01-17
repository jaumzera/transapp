package br.com.joaomassan.transapp.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountDto postAccount(@RequestBody AccountCreationRequest request) {
    return AccountDto.of(this.accountService.save(request.toEntity()));
  }

  @GetMapping("/{id}")
  public AccountDto getAccountById(@PathVariable Long id) {
    return AccountDto.of(this.accountService.findById(id));
  }
}
