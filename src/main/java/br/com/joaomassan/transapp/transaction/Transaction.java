package br.com.joaomassan.transapp.transaction;

import br.com.joaomassan.transapp.account.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

  @Id
  @SequenceGenerator(
      name = "transactionIdSeq",
      sequenceName = "transaction_id_seq",
      allocationSize = 1)
  @GeneratedValue(generator = "transactionIdSeq", strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @ManyToOne
  @JoinColumn(name = "operation_type", nullable = false)
  private OperationType operationType;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "event_date", nullable = false)
  private LocalDateTime eventDate;

  @Builder
  public Transaction(
      Long accountId, Long operationTypeId, BigDecimal amount, LocalDateTime eventDate) {
    this.account = new Account(Objects.requireNonNull(accountId, "accountId must not be null"));
    this.operationType =
        new OperationType(
            Objects.requireNonNull(operationTypeId, "opperationTypeId must not be null"));
    this.amount = Objects.requireNonNull(amount, "amount must not be null");
    this.eventDate = eventDate == null ? LocalDateTime.now() : eventDate;
  }
}
