package br.com.joaomassan.transapp.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.val;
import org.junit.jupiter.api.Test;

class AccountCreationRequestTest {

  @Test
  void shouldNotCreateAccountCreationRequestWithoutDocumentNumber() {
    assertThrows(NullPointerException.class, () -> new AccountCreationRequest(null, "Zakk Wylde"));
  }

  @Test
  void shouldNotCreateAccountCreationRequestWithoutName() {
    assertThrows(NullPointerException.class, () -> new AccountCreationRequest("11122233344", null));
  }

  @Test
  void toEntity_shouldGenerateAnAccount() {
    val account = new AccountCreationRequest("11122233344", "Zakk Wylde").toEntity();
    assertEquals("11122233344", account.getDocumentNumber());
    assertEquals("Zakk Wylde", account.getName());
  }
}
