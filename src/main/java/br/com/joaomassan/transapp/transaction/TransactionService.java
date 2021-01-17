package br.com.joaomassan.transapp.transaction;

import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  public Transaction save(Transaction transaction) {
    try {
      val savedTransaction = this.transactionRepository.saveAndFlush(transaction);
      return this.transactionRepository
          .findById(savedTransaction.getId())
          .orElseThrow(() -> new IllegalArgumentException("Error saving transaction"));
    } catch (DataIntegrityViolationException ex) {
      throw TransactionException.of("Invalid transaction data", ex);
    }
  }
}
