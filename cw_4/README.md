# Teoria Współbieżności

# Zadanie domowe

## 1 Wprowadzenie

Dany jest element czworokątny

![image](https://user-images.githubusercontent.com/92554920/202857607-c46f45c1-7d37-4ed4-9a46-17867f79804a.png)


Etykiety _N_ , _S_ , _W_ , _E_ oznaczają odpowiednie kierunki sąsiedztwa: North,
South, West, East. Dana jest następująca produkcja startow – generujące je-
den element _M_ ( 0 oznacza brak sąsiada w danym kierunku).

![image](https://user-images.githubusercontent.com/92554920/202857617-2742f670-8659-40df-be20-104d1e848c23.png)


Następnie dodajemy produkcje generującą sąsiada z lewej strony ( _∗_
oznacza że tutaj może być sąsiad lub nic).
Przykładowy wywód: _P I→P W→P W_

![image](https://user-images.githubusercontent.com/92554920/202857639-5e50bb75-92e0-4387-8e7e-6f775099286c.png)

## 2 Ćwiczenie

Ćwiczenie polega na wykonaniu następujących kroków:

- Proszę rozszerzyć gramatykę w taki sposób, aby była możliwa ge-
    neracja siatek prostokątnych, dwuwymiarowych, o ilości elementów
    _N×M_
- Proszę napisać ciąg produkcji w gramatyce generujący siatkę prosto-
    kątną o 3 _×_ 3 elementach
- Bazując na ciągu produkcji w gramatyce generującej przedstawioną
    siatkę, proszę wskazać alfabet w sensie teorii śladów
- Proszę napisać słowo (ciąg symboli z alfabetu) odpowiadających ge-
    neracji siatki prostokątnej
- Proszę wskazać relacje (nie)zależności dla alfabetu, w sensie teorii
    śladów
- Proszę przekształcić ciąg symboli (słowo) do postaci normalnej Fo-
    aty
- Proszę zaprojektować i zaimplementować algorytm współbieżny w
    oparciu o postać normalną Foaty. Parametr algorytmu to _N_ = ilość
    kwadratów na każdym boku siatki

Podczas projektowania i implementacji algorytmu wykorzystuje się roz-
szerzenie programu w języku JAVA wprowadzonego w ćwiczeniu 4.


## 3 Zadanie domowe (9 pkt.)

Ze względu na wprowadzający charakter ćwiczeń, oceniane są

1. Poprawność zdefiniowana alfabetu w sensie teorii śladów dla nowe-
    go wywodu **(2 pkt.)**
2. Poprawność zdefiniowania relacji zależności _D_ **(2 pkt.)**
3. Poprawność obliczenia klas Foaty **(2 pkt.)**
4. Poprawność implementacji schedulera dla nowych klas Foaty **(2 pkt.)**
5. Redakcja sprawozdania (pdf) **(1 pkt.)**

## 4 Format

Jedynym dopuszczalnym formatem dla części teoretycznej jest plik pdf.
Dopuszczalne jest przepisanie kodu schedulera na inny język pod warun-
kiem zachowania automatycznej kompilacji (odpowiednik maven). Całość
proszę dostarczyć w postaci archiwum zip.


