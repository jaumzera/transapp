package br.com.joaomassan.transapp.transaction;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionTest {

  private Transaction.TransactionBuilder builder;

  @BeforeEach
  void beforeAll() {
    this.builder =
        Transaction.builder().accountId(1L).amount(new BigDecimal("100.00")).operationTypeId(1L);
  }

  @Test
  void shouldNotCreateATransactionWithoutAccountId() {
    this.builder.accountId(null);
    assertThrows(NullPointerException.class, () -> this.builder.build());
  }

  @Test
  void shouldNotCreateATransactionWithoutOperationTypeId() {
    this.builder.operationTypeId(null);
    assertThrows(NullPointerException.class, () -> this.builder.build());
  }

  @Test
  void shouldNotCreateATransactionWithoutAmount() {
    this.builder.amount(null);
    assertThrows(NullPointerException.class, () -> this.builder.build());
  }
}
