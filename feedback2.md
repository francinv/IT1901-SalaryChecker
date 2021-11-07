# Tilbakemelding på innlevering 2

## Bygging

- fikk feil ved bygging med `mvn install` fordi en fil mangler:

`[INFO] Running salarychecker.core.EncryptDecryptTest
java.io.FileNotFoundException: /home/gitpod/Downloads/SalarycheckerKeystore.jks`

- fikk også feil ved bruk av kommandoen i README-fila: `mvn clean install -Dskiptests`
- bygging kom litt legnre når jeg skrev `mvn clean install -DskipTests=true`, men ui-testene så ut til å blir kjørt likevel, og da fikk jeg feil om en annen manglende fil:
`java.io.FileNotFoundException: /home/gitpod/Downloads/Accounts.json`

- får mange advarsler fra checkstyle

## Dokumentasjon

- pakke-diagrammet stemmer ikke med module-info.java

## Kodegjennomgang

### AbstractUser

- trenger hver AbstractUser sin egen instans av UserValidation?
- lag hjelpemetoder for å si fra til lyttere, så dere unngår kodeduplisering

### AdminUser

- kan godt kapsle inn bedre mellom sub- og super-klasse, f.eks. bruke super(...) i konstruktøren og get-metoder i stedet for direkte tilgang til felt

### User

- bruk super(...) for å sette felt i superklassen i konstruktøren
- ikke bruk implementasjonsklasser som type i deklarasjoner (felt og metoder) uten at det er nødvendig
- unngå kodeduplisering ifm. lyttere, som nevnt over
- getUserSaleList returnerer intern liste og bryter innkapsling
- isExistingUserSale: må være feil å ha `return false` inni løkka etter if-setningen

### UserSale

- hva slags data er egentlig salesperiod? er String riktig type å bruke?
- bruk `double` og ikke `Double` som felt-type
- det virker rart å ha difference-feltet og setDifference-metoden, det er jo bare å la getDifference returnere differansen

### Sale

- bruk egnede typer, String passer ikke for datoer, og enum-klasser er bedre enn String for data med et begrenset sett med verdier

### Accounts

- mye kodeduplisering i update-metodene, lag hjelpemetoder, f.eks. for å finne bruker
- userInfoStringChanged-metoden er merkelig: den sjekker om verdien allerede er satt, for så å kalle en metode for å sette den til samme verdi? hvis det bare er for å varsle lytterne, så virker det veldig bakvendt. med forbehold om at jeg ikke skjønte koden
- usersaleAdded: hvor trenger dere denne, når en kan kalle addUserSale direkte?

### Calculation

- her er det mange detaljer i koden som må være vanskelig å få rett og vedlikeholde, finnes det ingen annen tilnærming?

### SalaryCSVReader

- kan være ryddig å ha io-kode i en egen pakke
- csvToBean-metoden tar inn et url-argument, men det må være et filnavn og ikke en url!
- generelt er det bedre å ha metoder som tar inn en InputStream eller Reader, det er mer fleksibelt og testbart

### EncryptDecrypt

- problematisk bruk av filnavn, det kræsja i hvert fall bygget

### UserTest

- ikke så mye poeng å teste gettere og settere som ikke har noe logikk ut over det en får generert. logikken som bør testes er evt. validering, men da må dere teste med ugyldige verdier!
- dere sjekker at observatørlista blir oppdatert, men ikke at observatørene blir kalt!?

### SaleTest

- ikke så mye poeng å teste gettere og settere som ikke har noe logikk ut over det en får generert

### CalculationTest

- litt få tester sammenlignet med alle detaljene i `Calculation`

### SalaryCSVReaderTest

- dere må ikke fange opp unntak i setUp, testen skal feile når det er feil!

### SalaryCheckerApp

- ikke bra å en global (statisk) variabel!

### SettingsController

- felt som må settes utenifra kan initialiseres til null
- prøv å legge validering på hvert felt, så brukeren får tilbakemelding fra feltet f.eks. med rød ramme eller bakgrunnsfarge, i stedet for først når en velger lagre
- unngå å laste inn `HomePage.fxml` på nytt (en kom jo derfra), naviger heller tilbake til scenen en kom fra
