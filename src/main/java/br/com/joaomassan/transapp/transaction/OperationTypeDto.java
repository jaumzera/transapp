package br.com.joaomassan.transapp.transaction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationTypeDto {

  public static OperationTypeDto of(OperationType operationType) {
    return new OperationTypeDto(operationType.getId(), operationType.getDescription());
  }

  Long id;

  String description;
}
