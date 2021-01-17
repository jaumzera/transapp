package br.com.joaomassan.transapp.account;

class AccountException extends RuntimeException {

  static AccountException of(String message, Throwable throwable) {
    return new AccountException(message, throwable);
  }

  AccountException(String message, Throwable cause) {
    super(message, cause);
  }
}
