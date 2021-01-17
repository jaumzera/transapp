package br.com.joaomassan.transapp.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.Value;

@Value
public class AccountCreationRequest {

  String documentNumber;

  String name;

  @JsonCreator
  public AccountCreationRequest(
      @JsonProperty("documentNumber") String documentNumber, @JsonProperty("name") String name) {
    this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
    this.name = Objects.requireNonNull(name, "name must not be null");
  }

  public Account toEntity() {
    return new Account(null, this.documentNumber, this.name);
  }
}
