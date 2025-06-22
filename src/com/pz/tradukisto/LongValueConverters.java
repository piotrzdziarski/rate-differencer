package pl.allegro.finance.tradukisto;

import pl.allegro.finance.tradukisto.internal.LongToStringConverter;
import java.util.Objects;

import static pl.allegro.finance.tradukisto.internal.Container.englishContainer;
import static pl.allegro.finance.tradukisto.internal.Container.polishContainer;

public enum LongValueConverters {

    ENGLISH_LONG(englishContainer().getLongConverter()), 
    POLISH_LONG(polishContainer().getLongConverter());
   
    private final LongToStringConverter converter;

    LongValueConverters(LongToStringConverter converter) {
        this.converter = converter;
    }

    public String asWords(Long value) {
        Objects.requireNonNull(value);

        return converter.asWords(value);
    }
}
