package br.com.joaomassan.transapp.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {

  @Id
  @SequenceGenerator(name = "accountIdSeq", sequenceName = "account_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "accountIdSeq", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "document_number", unique = true)
  private String documentNumber;

  private String name;

  public Account(Long id) {
    this.id = id;
  }
}
