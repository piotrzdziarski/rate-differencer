package pl.allegro.finance.tradukisto.internal;

import pl.allegro.finance.tradukisto.internal.converters.BigDecimalToBankingMoneyConverter;
import pl.allegro.finance.tradukisto.internal.converters.HundredsToWordsConverter;
import pl.allegro.finance.tradukisto.internal.converters.NumberToWordsConverter;
import pl.allegro.finance.tradukisto.internal.languages.english.AmericanEnglishValues;
import pl.allegro.finance.tradukisto.internal.languages.english.EnglishValues;
import pl.allegro.finance.tradukisto.internal.languages.polish.PolishValues;

public final class Container {

    public static Container polishContainer() {
        return new Container(new PolishValues());
    }

    public static Container englishContainer() {
        return new Container(new EnglishValues());
    }

    public static Container americanEnglishContainer() {
        return new Container(new AmericanEnglishValues());
    }

    private final IntegerToStringConverter integerConverter;
    private final LongToStringConverter longConverter;
    private final BigDecimalToStringConverter bigDecimalConverter;

    private Container(BaseValues baseValues) {
        HundredsToWordsConverter hundredsToStringConverter = new HundredsToWordsConverter(
            baseValues.baseNumbers(),
            baseValues.twoDigitsNumberSeparator()
        );

        NumberToWordsConverter numberToWordsConverter = new NumberToWordsConverter(
            hundredsToStringConverter,
            baseValues.pluralForms()
        );
        integerConverter = numberToWordsConverter;
        longConverter = numberToWordsConverter;
        bigDecimalConverter = new BigDecimalToBankingMoneyConverter(
            integerConverter,
            baseValues.currency());
    }

    private Container(
        IntegerToStringConverter integerConverter,
        LongToStringConverter longConverter,
        BigDecimalToStringConverter bigDecimalConverter
    ) {
        this.integerConverter = integerConverter;
        this.longConverter = longConverter;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public IntegerToStringConverter getIntegerConverter() {
        return integerConverter;
    }

    public LongToStringConverter getLongConverter() {
        return longConverter;
    }

    public BigDecimalToStringConverter getBankingMoneyConverter() {
        return bigDecimalConverter;
    }
}
