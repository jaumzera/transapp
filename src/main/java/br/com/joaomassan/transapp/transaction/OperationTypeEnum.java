package br.com.joaomassan.transapp.transaction;

import br.com.joaomassan.transapp.account.Account;
import java.math.BigDecimal;

public enum OperationTypeEnum {
  INCREASE() {
    @Override
    public void execute(Account account, BigDecimal value) {
      account.increaseValue(value);
    }
  },
  DECREASE() {
    @Override
    public void execute(Account account, BigDecimal value) {
      account.decreaseCreditLimit(value);
    }
  };

  public abstract void execute(Account account, BigDecimal value);
}
