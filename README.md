Aplikacja pulpitowa w Javie generuje faktury w dolarach amerykańskich w formacie TXT na podstawia formularza GUI; w każdej jest ten sam towar. Niezbędne do rozliczenia różnic kursowych dane są zapisywane do pliku CSV. Dowód księgowy, dokumentujący rozliczenie różnic dla wszystkich dodanych faktur, jest generowany (w formacie TXT), po wpisaniu daty i danych płatności, i naciśnięciu przycisku 'Rozlicz'. Po rozliczeniu lista faktur jest czyszczona.
Dokumenty są generowane w folderze "documents" na podstawie szablonów z "templates".
Kursy USD/PLN są pobierane z API Narodowego Banku Polskiego. Dodawanie faktur i rozliczanie są w osobnych wątkach.
Nazwy kolejno generowanych dokumentów nie są związane z numerami podawanymi w formularzach — te są zapisywane do plików — tylko kolejno przypisywane na podstawie liczby dokumentów w miesiącu. Jest to zamierzone zachowanie.
Do generacji wartości pienieżnych słownie, została użyta biblioteka Allegro Tradukisto.

## Uruchomienie
```
java -jar Rate_Differencer.jar
```

