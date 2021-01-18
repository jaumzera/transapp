package br.com.joaomassan.transapp.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.joaomassan.transapp.account.Account;
import java.math.BigDecimal;
import lombok.val;
import org.junit.jupiter.api.Test;

class OperationTypeEnumTest {

  @Test
  void increaseTest() {
    val acc = new Account(1L, "Jaumzera", "00412333311", new BigDecimal("1500.00"));
    OperationTypeEnum.DECREASE.execute(acc, new BigDecimal("1500.00"));
    assertEquals(BigDecimal.ZERO.compareTo(acc.getAvailableCreditLimit()), 0);
  }

  @Test
  void decreaseTest() {
    val acc = new Account(1L, "Jaumzera", "00412333311", new BigDecimal("1500.00"));
    OperationTypeEnum.INCREASE.execute(acc, new BigDecimal("1500.00"));
    assertEquals(new BigDecimal("3000.00").compareTo(acc.getAvailableCreditLimit()), 0);
  }
}
