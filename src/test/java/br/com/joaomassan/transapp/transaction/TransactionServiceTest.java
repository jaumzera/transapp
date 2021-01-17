package br.com.joaomassan.transapp.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

class TransactionServiceTest {

  private Transaction transaction;

  private TransactionRepository transactionRepository;

  private TransactionService transactionService;

  @BeforeEach
  void beforeEach() {
    this.transactionRepository = Mockito.mock(TransactionRepository.class);
    this.transactionService = new TransactionService(this.transactionRepository);
    this.transaction =
        Transaction.builder()
            .accountId(1L)
            .operationTypeId(1L)
            .amount(new BigDecimal("199.97"))
            .build();
  }

  @Test
  void save_shouldCreateATransaction() {
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
    Mockito.verify(this.transactionRepository).saveAndFlush(this.transaction);
    assertEquals(savedTransaction, this.transaction);
    assertEquals(1L, savedTransaction.getId());
  }

  @Test
  void save_shouldNotCreateWithInvalidDDataIntegrity() {
    Mockito.when(this.transactionRepository.saveAndFlush(this.transaction))
        .thenThrow(TransactionException.of("Invalid transaction data", null));
    assertThrows(TransactionException.class, () -> this.transactionService.save(this.transaction));
    Mockito.verify(this.transactionRepository).saveAndFlush(this.transaction);
  }
}
