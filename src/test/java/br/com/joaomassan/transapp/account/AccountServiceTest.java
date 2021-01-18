package br.com.joaomassan.transapp.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import javax.persistence.PersistenceException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.dao.DataIntegrityViolationException;

class AccountServiceTest {

  private final Account account =
      new Account(null, "00011122233", "Muddy Waters", new BigDecimal("1000.00"));

  private AccountRepository accountRepository;

  private AccountService accountService;

  @BeforeEach
  void beforeEach() {
    this.account.setId(null);
    this.accountRepository = Mockito.mock(AccountRepository.class);
    this.accountService = new AccountService(accountRepository);
  }

  @Test
  void save_shouldSaveAnAccount() {
    Mockito.when(this.accountRepository.saveAndFlush(this.account))
        .then(
            (Answer<Account>)
                invocationOnMock -> {
                  AccountServiceTest.this.account.setId(1L);
                  return account;
                });
    val savedAccount = accountService.save(this.account);
    Mockito.verify(this.accountRepository).saveAndFlush(this.account);
    assertEquals(this.account, savedAccount);
    assertEquals(1L, savedAccount.getId());
  }

  @Test
  void save_shouldThrowAnAccountAlreadyExistsException() {
    Mockito.when(this.accountRepository.saveAndFlush(this.account))
        .thenThrow(DataIntegrityViolationException.class);
    assertThrows(AccountAlreadyExistsException.class, () -> this.accountService.save(this.account));
    Mockito.verify(accountRepository).saveAndFlush(this.account);
  }

  @Test
  void save_shouldThrowAnAccountException() {
    Mockito.when(this.accountRepository.saveAndFlush(this.account))
        .thenThrow(PersistenceException.class);
    assertThrows(AccountException.class, () -> this.accountService.save(this.account));
    Mockito.verify(this.accountRepository).saveAndFlush(this.account);
  }
}
