package guru.springframework;

import java.util.Objects;

public class Money implements Expression {
  protected int amount;
  protected String currency;


  protected Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public String currency() {
    return currency;
  }
  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return amount == money.amount && Objects.equals(currency, money.currency);
  }

  @Override
  public String toString() {
    return "Money{" + "amount=" + amount + ", currency='" + currency + '\'' + '}';
  }

  public Money times(int multiplier) {
    return new Money(amount * multiplier, this.currency);
  }

  @Override
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  @Override
  public Money reduce(Bank bank, String to) {
    int rate = bank.rate(currency, to);
    return new Money(amount / rate, to);
  }
}
