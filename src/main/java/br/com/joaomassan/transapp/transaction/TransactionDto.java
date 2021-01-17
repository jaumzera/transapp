package br.com.joaomassan.transapp.transaction;

import br.com.joaomassan.transapp.account.AccountDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionDto {

  public static TransactionDto of(Transaction transaction) {
    return new TransactionDto(
        transaction.getId(),
        AccountDto.of(transaction.getAccount()),
        OperationTypeDto.of(transaction.getOperationType()),
        transaction.getAmount(),
        transaction.getEventDate());
  }

  Long id;

  AccountDto account;

  OperationTypeDto operationType;

  BigDecimal amount;

  LocalDateTime eventDate;
}
