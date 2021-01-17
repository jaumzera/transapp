package br.com.joaomassan.transapp.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Value;

@Value
public class TransactionCreationRequest {

  Long accountId;

  Long operationTypeId;

  BigDecimal amount;

  @JsonCreator
  public TransactionCreationRequest(
      @JsonProperty("accountId") Long accountId,
      @JsonProperty("operationTypeId") Long operationTypeId,
      @JsonProperty("amount") BigDecimal amount) {
    this.accountId = Objects.requireNonNull(accountId, "accountId must not be null");
    this.operationTypeId =
        Objects.requireNonNull(operationTypeId, "operationTypeId must not be null");
    this.amount = Objects.requireNonNull(amount, "amount must not be null");
  }

  public Transaction toEntity() {
    return Transaction.builder()
        .accountId(this.accountId)
        .operationTypeId(this.operationTypeId)
        .amount(this.amount)
        .build();
  }
}
