package br.com.joaomassan.transapp.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import lombok.val;
import org.junit.jupiter.api.Test;

class AccountCreationRequestTest {

  @Test
  void shouldNotCreateAccountCreationRequestWithoutDocumentNumber() {
    assertThrows(
        NullPointerException.class,
        () -> new AccountCreationRequest(null, "Zakk Wylde", new BigDecimal("1000.00")));
  }

  @Test
  void shouldNotCreateAccountCreationRequestWithoutName() {
    assertThrows(
        NullPointerException.class,
        () -> new AccountCreationRequest("11122233344", null, new BigDecimal("1000.00")));
  }

  @Test
  void toEntity_shouldGenerateAnAccount() {
    val account =
        new AccountCreationRequest("11122233344", "Zakk Wylde", new BigDecimal("1000.00"))
            .toEntity();
    assertEquals("11122233344", account.getDocumentNumber());
    assertEquals("Zakk Wylde", account.getName());
  }

  /// FIXME add a new test for credit
}
