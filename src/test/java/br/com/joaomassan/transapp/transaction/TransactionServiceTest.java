package br.com.joaomassan.transapp.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.joaomassan.transapp.account.Account;
import br.com.joaomassan.transapp.account.AccountException;
import br.com.joaomassan.transapp.account.AccountService;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.test.annotation.DirtiesContext;

class TransactionServiceTest {

  private Transaction transaction;

  private AccountService accountService;

  private TransactionRepository transactionRepository;

  private TransactionService transactionService;

  @BeforeEach
  void beforeEach() {
    this.transactionRepository = Mockito.mock(TransactionRepository.class);
    OperationTypeRepository operationTypeRepository = Mockito.mock(OperationTypeRepository.class);
    this.accountService = Mockito.mock(AccountService.class);
    this.transactionService =
        new TransactionService(
            this.accountService, this.transactionRepository, operationTypeRepository);
    this.transaction =
        Transaction.builder()
            .accountId(1L)
            .operationTypeId(1L)
            .amount(new BigDecimal("199.97"))
            .build();

    Mockito.when(operationTypeRepository.findById(1L))
        .thenReturn(
            Optional.of(new OperationType(1L, "COMPRA A VISTA", OperationTypeEnum.DECREASE)));
  }

  @Test
  void save_shouldCreateATransaction() {
    Mockito.when(this.accountService.findById(1L))
        .thenReturn(new Account(1L, "Déx Mi", "22233333311", new BigDecimal("1000.00")));
    Mockito.when(this.transactionRepository.saveAndFlush(this.transaction))
        .then(
            (Answer<Transaction>)
                invocationOnMock -> {
                  TransactionServiceTest.this.transaction.setId(1L);
                  return TransactionServiceTest.this.transaction;
                });
    Mockito.when(this.transactionRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(this.transaction));
    val savedTransaction = this.transactionService.save(this.transaction);
    Mockito.verify(this.accountService).findById(1L);
    Mockito.verify(this.transactionRepository).saveAndFlush(this.transaction);
    assertEquals(savedTransaction, this.transaction);
    assertEquals(1L, savedTransaction.getId());
  }

  @Test
  void save_shouldNotCreateATransactionForAnAccountWithoutCreditLimit() {
    Mockito.when(this.accountService.findById(1L))
        .thenReturn(new Account(1L, "Déx Mi", "22233333311", new BigDecimal("100.00")));
    Mockito.when(this.transactionRepository.saveAndFlush(this.transaction))
        .then(
            (Answer<Transaction>)
                invocationOnMock -> {
                  TransactionServiceTest.this.transaction.setId(1L);
                  return TransactionServiceTest.this.transaction;
                });
    Mockito.when(this.transactionRepository.findById(1L)).thenReturn(Optional.of(this.transaction));
    assertThrows(AccountException.class, () -> this.transactionService.save(this.transaction));
    Mockito.verify(this.accountService).findById(1L);
  }

  @Test
  void save_shouldNotCreateWithInvalidDataIntegrity() {
    Mockito.when(this.transactionRepository.saveAndFlush(this.transaction))
        .thenThrow(TransactionException.of("Invalid transaction data", null));
    Mockito.when(this.accountService.findById(1L))
        .thenReturn(new Account(1L, "Déx Mi", "22233333311", new BigDecimal("1000.00")));
    assertThrows(TransactionException.class, () -> this.transactionService.save(this.transaction));
    Mockito.verify(this.transactionRepository).saveAndFlush(this.transaction);
  }
}
