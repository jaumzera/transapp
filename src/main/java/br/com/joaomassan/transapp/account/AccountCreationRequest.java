package br.com.joaomassan.transapp.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Value;

@Value
public class AccountCreationRequest {

  String documentNumber;

  String name;

  BigDecimal initialCreditLimit;

  @JsonCreator
  public AccountCreationRequest(
      @JsonProperty("documentNumber") String documentNumber,
      @JsonProperty("name") String name,
      @JsonProperty("initialCreditLimit") BigDecimal initialCreditLimit) {
    this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
    this.name = Objects.requireNonNull(name, "name must not be nu ll");
    this.initialCreditLimit =
        Objects.requireNonNull(initialCreditLimit, "initialCreditLimit must not be null");
  }

  public Account toEntity() {
    return new Account(null, this.documentNumber, this.name, this.initialCreditLimit);
  }
}
