package pl.allegro.finance.tradukisto;

import pl.allegro.finance.tradukisto.internal.Container;
import pl.allegro.finance.tradukisto.internal.IntegerToStringConverter;
import pl.allegro.finance.tradukisto.internal.support.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public enum ValueConverters {
    ENGLISH_INTEGER(Container.englishContainer().getIntegerConverter(), "en"),
    POLISH_INTEGER(Container.polishContainer().getIntegerConverter(), "pl");

    private final IntegerToStringConverter converter;
    private final List<String> languageCodes;

    ValueConverters(IntegerToStringConverter converter, String languageCodes) {
        this(converter, Collections.singletonList(languageCodes));
    }

    ValueConverters(IntegerToStringConverter converter, List<String> languageCodes) {
        this.converter = converter;
        this.languageCodes = languageCodes;
    }

    public static ValueConverters getByLocaleOrDefault(Locale locale, ValueConverters defaultConverter) {
        Objects.requireNonNull(locale);

        String languageCode = hasSpecifiedScript(locale)
            ? getLanguageCodeFor(locale.getLanguage(), locale.getScript())
            : locale.getLanguage();

        return getByLanguageCodeOrDefault(languageCode, defaultConverter);
    }

    public static ValueConverters getByLanguageCodeOrDefault(String languageCode, ValueConverters defaultConverter) {
        Objects.requireNonNull(languageCode);
        Assert.isFalse(languageCode.isEmpty());

        return Arrays.stream(values())
                .filter(it -> it.languageCodes.contains(languageCode))
                .findFirst()
                .orElse(defaultConverter);
    }

    private static boolean hasSpecifiedScript(Locale locale) {
        return !locale.getScript().isEmpty();
    }

    private static String getLanguageCodeFor(String language, String script) {
        return new Locale.Builder()
                .setLanguage(language)
                .setScript(script)
                .build()
                .toString();
    }

    public String asWords(Integer value) {
        Objects.requireNonNull(value);

        return converter.asWords(value);
    }
}
