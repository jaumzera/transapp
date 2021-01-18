package br.com.joaomassan.transapp.transaction;

import br.com.joaomassan.transapp.account.AccountService;
import lombok.val;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  private final AccountService accountService;

  private final TransactionRepository transactionRepository;

  private final OperationTypeRepository operationTypeRepository;

  public TransactionService(
      AccountService accountService,
      TransactionRepository transactionRepository,
      OperationTypeRepository operationTypeRepository) {
    this.accountService = accountService;
    this.transactionRepository = transactionRepository;
    this.operationTypeRepository = operationTypeRepository;
  }

  @Transactional
  public Transaction save(Transaction transaction) {
    try {
      val account = this.accountService.findById(transaction.getAccount().getId());

      /// FIXME o OperationType poderia receber direto o obj Transaction
      this.operationTypeRepository
          .findById(transaction.getOperationType().getId())
          .orElseThrow(() -> TransactionException.of("OperationType not found", null))
          .getType()
          .execute(account, transaction.getAmount());

      val savedTransaction = this.transactionRepository.saveAndFlush(transaction);

      return this.transactionRepository
          .findById(savedTransaction.getId())
          .orElseThrow(() -> new IllegalArgumentException("Error saving transaction"));
    } catch (DataIntegrityViolationException ex) {
      throw TransactionException.of("Invalxid transaction data", ex);
    }
  }
}
