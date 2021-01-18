package br.com.joaomassan.transapp.transaction;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operation_types")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OperationType {

  @Id
  @SequenceGenerator(
      name = "operationTypeIdSeq",
      sequenceName = "operation_type_id_seq",
      allocationSize = 1)
  @GeneratedValue(generator = "operationTypeIdSeq", strategy = GenerationType.SEQUENCE)
  private Long id;

  private String description;

  @Enumerated(EnumType.STRING)
  private OperationTypeEnum type = OperationTypeEnum.DECREASE;

  public OperationType(Long id) {
    this.id = id;
  }
}
