package br.com.joaomassan.transapp.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

  public static AccountDto of(Account account) {
    return new AccountDto(account.getId(), account.getDocumentNumber(), account.getName());
  }

  Long userId;

  String documentNumber;

  String name;
}
