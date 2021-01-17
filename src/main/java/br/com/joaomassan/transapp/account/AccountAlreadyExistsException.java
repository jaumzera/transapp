package br.com.joaomassan.transapp.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountAlreadyExistsException extends AccountException {

  public static AccountAlreadyExistsException of(Account account, Throwable throwable) {
    return new AccountAlreadyExistsException(
        "Account already exists for document number: " + account.getDocumentNumber(), throwable);
  }

  private AccountAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
