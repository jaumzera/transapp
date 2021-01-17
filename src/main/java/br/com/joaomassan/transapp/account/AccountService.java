package br.com.joaomassan.transapp.account;

import br.com.joaomassan.transapp.commons.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional
  public Account save(Account account) {
    try {
      return this.accountRepository.saveAndFlush(account);
    } catch (DataIntegrityViolationException ex) {
      throw AccountAlreadyExistsException.of(account, ex);
    } catch (Exception ex) {
      throw AccountException.of(ex.getMessage(), ex);
    }
  }

  public Account findById(Long accountId) {
    return this.accountRepository
        .findById(accountId)
        .orElseThrow(
            () -> NotFoundException.withMessage(String.format("Account #%d not found", accountId)));
  }
}
