package br.com.joaomassan.transapp.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TransactionException extends RuntimeException {

  static TransactionException of(String message, Throwable throwable) {
    return new TransactionException(message, throwable);
  }

  private TransactionException(String message, Throwable cause) {
    super(message, cause);
  }
}
