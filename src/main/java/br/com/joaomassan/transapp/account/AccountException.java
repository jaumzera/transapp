package br.com.joaomassan.transapp.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountException extends RuntimeException {

  public static AccountException of(String message, Throwable throwable) {
    return new AccountException(message, throwable);
  }

  AccountException(String message, Throwable cause) {
    super(message, cause);
  }
}
