package guru.springframework;

import java.util.HashMap;

public class Bank {

  private final HashMap<Pair, Integer> rateMap = new HashMap<>();
  public Money reduce(Expression source, String currencyFormatter) {
    return source.reduce(this, currencyFormatter);
  }

  public int rate(String from, String to) {
    return from.equals(to) ? 1 : rateMap.get(new Pair(from, to));
  }

  public void addRate(String from, String to, int rate) {
    rateMap.put(new Pair(from, to), rate);
  }
}
