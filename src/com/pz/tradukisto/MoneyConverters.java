package pl.allegro.finance.tradukisto;

import pl.allegro.finance.tradukisto.internal.BigDecimalToStringConverter;
import pl.allegro.finance.tradukisto.internal.Container;

import java.math.BigDecimal;
import java.util.Objects;

import static pl.allegro.finance.tradukisto.internal.Container.americanEnglishContainer;
import static pl.allegro.finance.tradukisto.internal.Container.englishContainer;
import static pl.allegro.finance.tradukisto.internal.Container.polishContainer;
public enum MoneyConverters {

    ENGLISH_BANKING_MONEY_VALUE(englishContainer().getBankingMoneyConverter()),
    AMERICAN_ENGLISH_BANKING_MONEY_VALUE(americanEnglishContainer().getBankingMoneyConverter()),
    POLISH_BANKING_MONEY_VALUE(polishContainer().getBankingMoneyConverter());

    private final BigDecimalToStringConverter converter;

    MoneyConverters(BigDecimalToStringConverter converter) {
        this.converter = converter;
    }

    public String asWords(BigDecimal value) {
        Objects.requireNonNull(value);

        return converter.asWords(value);
    }

    public String asWords(BigDecimal value, String currencySymbol) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(currencySymbol);

        return converter.asWords(value, currencySymbol);
    }
}
