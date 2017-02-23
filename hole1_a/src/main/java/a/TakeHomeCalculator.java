package a;

import static a.Money.money;
import static java.util.Arrays.stream;

public class TakeHomeCalculator {

    private final int percent;

    public TakeHomeCalculator(int percent) {
        this.percent = percent;
    }

    public Money netAmount(Money first, Money... rest) {
        Money total = stream(rest).reduce(first, (money, that) -> money.plus(that));
        Double amount = total.value * (percent / 100d);
        Money tax = money(amount.intValue(), total.currency);

        if (total.currency.equals(tax.currency)) {
            return money(total.value - tax.value, total.currency);
        }
        throw new Incalculable();
    }

}
